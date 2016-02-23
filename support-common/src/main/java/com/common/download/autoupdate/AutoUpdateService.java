package com.common.download.autoupdate;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.common.R;
import com.common.download.DownloadManager;
import com.common.download.entity.DownloadEntry;
import com.common.download.notify.DataWatcher;
import com.common.utils.Logger;
import com.common.utils.PackageUtils;
import com.constants.fixed.GlobalConstants;
import com.constants.level.DownloadStatusLevel;

/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AutoUpdateService extends Service {
    private final String url = "http://gh-game.oss-cn-hangzhou.aliyuncs.com/1434794302961350.apk";
    ProgressDialog pBar;
    private MyBinder myBinder = new MyBinder();
    private DownloadEntry entry = new DownloadEntry(url);
    private DataWatcher dataWatcher = new DataWatcher() {

        @Override
        public void onDataChanged(DownloadEntry data) {
            if (data.getUrl().equals(entry.getUrl())) {
                entry = data;
                pBar.setMax(entry.getTotalLength());
                pBar.setProgress(entry.getCurrentLength());
                if (entry.getStatus() == DownloadStatusLevel.DONE.value()) {//下载完成
                    Logger.d("done ------>");
                    PackageUtils.installNormal(AutoUpdateService.this, GlobalConstants.DOWNLOAD_PATH + "1434794302961350.apk");
                }
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        download();
    }

    public void dialogBuilder() {
        DownloadManager.getInstance(this).addObserver(dataWatcher);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.newUpdateAvailable);
        builder.setMessage("aa")
                .setPositiveButton(R.string.dialogPositiveButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checkUpdate();
                        dialog.dismiss();
                        pBar.show();
                    }
                })
                .setNegativeButton(R.string.dialogNegativeButton, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        //dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 检查是否有升级
     */
    private void checkUpdate() {
        entry.setName("1434794302961350.apk");
        DownloadManager.getInstance(this).add(entry);
    }

    private void download() {
        pBar = new ProgressDialog(this);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
        pBar.setProgress(0);
        pBar.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        //pBar.setCancelable(false);
    }

    public class MyBinder extends Binder {

        public AutoUpdateService getService() {
            return AutoUpdateService.this;
        }
    }

}
