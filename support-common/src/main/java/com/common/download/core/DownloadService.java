package com.common.download.core;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.common.download.DownloadConfig;
import com.common.download.db.DBController;
import com.common.download.entity.DownloadEntry;
import com.common.download.notify.DataChanger;
import com.common.utils.Logger;
import com.constants.fixed.GlobalConstants;
import com.constants.level.DownloadStatusLevel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author tianlifei
 * @email 179840045@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des Service to manager download tasks.
 */
public class DownloadService extends Service {
    public static final int NOTIFY_DOWNLOADING = 1;
    public static final int NOTIFY_UPDATING = 2;
    public static final int NOTIFY_PAUSED_OR_CANCELLED = 3;
    public static final int NOTIFY_COMPLETED = 4;
    public static final int NOTIFY_ERROR = 5;
    public static final int NOTIFY_CONNECTING = 6;
    public static final int NOTIFY_NOT_ENOUGH_SIZE = 7;

    private HashMap<String, DownloadTask> mDownloadingTasks;
    private LinkedBlockingQueue<DownloadEntry> mWaitingQueue;

    private ExecutorService mExecutor;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DownloadEntry entry = (DownloadEntry) msg.obj;
            switch (msg.what) {
                case NOTIFY_COMPLETED:
                case NOTIFY_PAUSED_OR_CANCELLED:
                case NOTIFY_ERROR:
                    checkNext(entry);
                    break;

                case NOTIFY_NOT_ENOUGH_SIZE:
                    Toast.makeText(getApplicationContext(), "存储卡空间不足，请清理！", Toast.LENGTH_SHORT).show();
                    checkNext(entry);
                    break;
            }
            DataChanger.getInstance(getApplication()).updateStatus(entry);
        }

    };
    private DataChanger dataChanger;
    private DBController dbController;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDownloadingTasks = new HashMap<String, DownloadTask>();
        mWaitingQueue = new LinkedBlockingQueue<DownloadEntry>();

        mExecutor = Executors.newCachedThreadPool();
        dataChanger = DataChanger.getInstance(getApplicationContext());
        dbController = DBController.getInstance(getApplicationContext());
        intializeDownload();

    }

    //防止App进程被强杀时数据丢失
    private void intializeDownload() {
        List<DownloadEntry> mDownloadEtries = dbController.queryAll();
        if (mDownloadEtries != null) {
            for (DownloadEntry entry : mDownloadEtries) {
                if (entry.getStatus() == DownloadStatusLevel.DOWNLOADING.value() || entry.getStatus() == DownloadStatusLevel.WAITING.value()) {
                    entry.setStatus(DownloadStatusLevel.PAUSE.value());
                    if (DownloadConfig.getInstance().isRecoverDownloadWhenStart()) {
                        if (entry.getIsSupportRange()) {
                            entry.setStatus(DownloadStatusLevel.PAUSE.value());
                        } else {
                            entry.setStatus(DownloadStatusLevel.IDLE.value());
                            entry.reset();
                        }
                        addDownload(entry);
                    } else {
                        if (entry.getIsSupportRange()) {
                            entry.setStatus(DownloadStatusLevel.PAUSE.value());
                        } else {
                            entry.setStatus(DownloadStatusLevel.IDLE.value());
                            entry.reset();
                        }
                        dbController.newOrUpdate(entry);
                    }
                }
                dataChanger.addToOperatedEntryMap(entry.getUrl(), entry);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            int action = intent.getIntExtra(GlobalConstants.KEY_DOWNLOAD_ACTION, -1);
            DownloadEntry entry = (DownloadEntry) intent.getSerializableExtra(GlobalConstants.KEY_DOWNLOAD_ENTRY);
            /*****防止App进程被强杀时数据丢失*****/
            if (entry != null && dataChanger.containsDownloadEntry(entry.getUrl())) {
                entry = dataChanger.queryDownloadEntryByUrl(entry.getUrl());
            }

            switch (action) {
                case GlobalConstants.KEY_DOWNLOAD_ACTION_ADD:
                    addDownload(entry);
                    break;

                case GlobalConstants.KEY_DOWNLOAD_ACTION_PAUSE:
                    pauseDownload(entry);
                    break;

                case GlobalConstants.KEY_DOWNLOAD_ACTION_RESUME:
                    resumeDownload(entry);
                    break;

                case GlobalConstants.KEY_DOWNLOAD_ACTION_CANCEL:
                    cancelDownload(entry);
                    break;

                case GlobalConstants.KEY_DOWNLOAD_ACTION_PAUSE_ALL:
                    pauseAllDownload();
                    break;

                case GlobalConstants.KEY_DOWNLOAD_ACTION_RECOVER_ALL:
                    recoverAllDownload();
                    break;

                default:
                    break;

            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void recoverAllDownload() {
        ArrayList<DownloadEntry> mRecoverableEntries = DataChanger.getInstance(getApplication()).queryAllRecoverableEntries();
        if (mRecoverableEntries == null) return;

        for (DownloadEntry entry : mRecoverableEntries) {
            addDownload(entry);
        }
        Logger.d("DownloadService==>recoverAllDownload" + "***Task Size:" + mDownloadingTasks.size() + "***Waiting Queue:" + mWaitingQueue.size());
    }

    private void pauseAllDownload() {
        while (mWaitingQueue.iterator().hasNext()) {
            DownloadEntry entry = mWaitingQueue.poll();
            entry.setStatus(DownloadStatusLevel.PAUSE.value());
            DataChanger.getInstance(getApplication()).updateStatus(entry);
        }

        for (Map.Entry<String, DownloadTask> entry : mDownloadingTasks.entrySet()) {
            entry.getValue().pause();
        }
        mDownloadingTasks.clear();
        Logger.d("DownloadService==>pauseAllDownload");
    }

    private void checkNext(DownloadEntry entry) {
        mDownloadingTasks.remove(entry.getUrl());
        DownloadEntry newEntry = mWaitingQueue.poll();
        if (newEntry != null) {
            startDownload(newEntry);
        }
    }

    ;

    private void cancelDownload(DownloadEntry entry) {
        DownloadTask task = mDownloadingTasks.remove(entry.getUrl());
        if (task != null) {
            task.cancel();
            Logger.d("DownloadService==>pauseDownload#####cancel downloading task"
                    + "***Task Size:" + mDownloadingTasks.size()
                    + "***Waiting Queue:" + mWaitingQueue.size());
        } else {
            mWaitingQueue.remove(entry);
            entry.setStatus(DownloadStatusLevel.CANCEL.value());
            DataChanger.getInstance(getApplication()).updateStatus(entry);
            Logger.d("DownloadService==>pauseDownload#####cancel waiting queue!"
                    + "***Task Size:" + mDownloadingTasks.size()
                    + "***Waiting Queue:" + mWaitingQueue.size());
        }
    }

    private void resumeDownload(DownloadEntry entry) {
        addDownload(entry);
        Logger.d("DownloadService==>resumeDownload"
                + "***Task Size:" + mDownloadingTasks.size()
                + "***Waiting Queue:" + mWaitingQueue.size());
    }

    private void pauseDownload(DownloadEntry entry) {
        DownloadTask task = mDownloadingTasks.remove(entry.getUrl());
        if (task != null) {
            Logger.d("DownloadService==>pauseDownload#####pause downloading task"
                    + "***Task Size:" + mDownloadingTasks.size()
                    + "***Waiting Queue:" + mWaitingQueue.size());
            task.pause();
        } else {
            mWaitingQueue.remove(entry);
            entry.setStatus(DownloadStatusLevel.PAUSE.value());
            DataChanger.getInstance(getApplication()).updateStatus(entry);
            Logger.d("DownloadService==>pauseDownload#####pause waiting queue!"
                    + "***Task Size:" + mDownloadingTasks.size()
                    + "***Waiting Queue:" + mWaitingQueue.size());
        }

    }

    private void addDownload(DownloadEntry entry) {
        checkDownloadPath(entry);
        if (isDownloadEntryRepeted(entry)) {
            return;
        }
        if (mDownloadingTasks.size() >= DownloadConfig.getInstance().getMax_download_tasks()) {
            mWaitingQueue.offer(entry);
            entry.setStatus(DownloadStatusLevel.WAITING.value());
            DataChanger.getInstance(getApplication()).updateStatus(entry);
            Logger.d("DownloadService==>addDownload#####bigger than max_tasks"
                    + "***Task Size:" + mDownloadingTasks.size()
                    + "***Waiting Queue:" + mWaitingQueue.size());
        } else {
            Logger.d("DownloadService==>addDownload#####start tasks"
                    + "***Task Size:" + mDownloadingTasks.size()
                    + "***Waiting Queue:" + mWaitingQueue.size());
            startDownload(entry);
        }
    }

    private void startDownload(DownloadEntry entry) {
        DownloadTask task = new DownloadTask(entry, mHandler, mExecutor);
        mDownloadingTasks.put(entry.getUrl(), task);
        Logger.d("DownloadService==>startDownload"
                + "***Task Size:" + mDownloadingTasks.size()
                + "***Waiting Queue:" + mWaitingQueue.size());
        task.start();
    }

    private void checkDownloadPath(DownloadEntry entry) {
        Logger.d("DownloadService==>checkDownloadPath()");
        File file = new File(GlobalConstants.DOWNLOAD_PATH + entry.getUrl().substring(entry.getUrl().lastIndexOf("/") + 1));
        if (file != null && !file.exists()) {
            entry.reset();
            Logger.d("DownloadService==>checkDownloadPath()#####" + entry.getName() + "'s cache is not exist, restart download!");
        }
    }

    private boolean isDownloadEntryRepeted(DownloadEntry entry) {
        if (mDownloadingTasks.get(entry.getUrl()) != null) {
            Logger.d("DownlaodService==>isDownloadEntryRepeted()##### The downloadEntry is in downloading tasks!!");
            return true;
        }

        if (mWaitingQueue.contains(entry)) {
            Logger.d("DownlaodService==>isDownloadEntryRepeted()##### The downloadEntry is in waiting queue!!");
            return true;
        }
        return false;
    }

}
