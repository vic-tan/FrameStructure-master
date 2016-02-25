package com.common.download.autoupdate;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.Html;
import android.view.WindowManager;

import com.common.R;
import com.common.download.DownloadManager;
import com.common.download.entity.DownloadEntry;
import com.common.download.notify.DataWatcher;
import com.common.engine.interf.IHttpTaskCallBack;
import com.common.http.base.BaseHttpParams;
import com.common.http.base.RequestBean;
import com.common.http.task.HttpTask;
import com.common.utils.AppUtils;
import com.common.utils.JsonUtils;
import com.common.utils.PackageUtils;
import com.common.utils.ResUtils;
import com.constants.fixed.GlobalConstants;
import com.constants.fixed.UrlConstants;
import com.constants.level.DownloadStatusLevel;

/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AutoUpdateService extends Service {
    ProgressDialog pBar;
    private MyBinder myBinder = new MyBinder();
    private DownloadEntry entry;
    private AppAutoUpdateBean appAutoUpdateBean;
    private DataWatcher dataWatcher = new DataWatcher() {

        @Override
        public void onDataChanged(DownloadEntry data) {
            if (data.getUrl().equals(entry.getUrl())) {
                entry = data;
                pBar.setMax(entry.getTotalLength());
                pBar.setProgress(entry.getCurrentLength());
                if (entry.getStatus() == DownloadStatusLevel.DONE.value()) {//下载完成
                    if (null != pBar && pBar.isShowing())
                        pBar.dismiss();
                    PackageUtils.installNormal(AutoUpdateService.this, entry.getSaveUrl());
                }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }


    /**
     * 检查是否有升级
     */
    public void checkAppUpdate() {
        //TODO 删除
        //BaseApplication.daoMaster.newSession().getDownloadEntryDao().deleteByKey(Builder);

        HttpTask.post(new RequestBean(this, BaseHttpParams.baseParams(UrlConstants.APP_VERSION_UPDATE)), new IHttpTaskCallBack() {
            @Override
            public void taskCallBack(RequestBean requestBean) {
                switch (requestBean.getRequestLevel()) {
                    case SUCCESS:
                        if (null != requestBean.getBaseJson()) {
                            appAutoUpdateBean = JsonUtils.parseToObjectBean(requestBean.getBaseJson().getData(), AppAutoUpdateBean.class);
                            if (Integer.parseInt(appAutoUpdateBean.getVersion_code()) > AppUtils.getVersionCode(AutoUpdateService.this)) {
                                checkAppUpdateBuilder();
                            }
                        }
                }
            }
        });
    }

    /**
     * 开始下载升级app
     */
    private void startDownloadApp() {
        entry = new DownloadEntry(appAutoUpdateBean.getUrl());
        entry.setName(appAutoUpdateBean.getName());
        entry.setSaveUrl(GlobalConstants.DOWNLOAD_PATH + entry.getName());
        DownloadManager.getInstance(this).add(entry);
    }


    /**
     * 升级提示框
     */
    private void checkAppUpdateBuilder() {
        DownloadManager.getInstance(this).addObserver(dataWatcher);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(ResUtils.getStr(R.string.auto_update_new_update_available));
        builder.setMessage(Html.fromHtml(appAutoUpdateBean.getDesc()))
                .setPositiveButton(ResUtils.getStr(R.string.auto_update_dialog_positive_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startDownloadApp();
                        downloadingBuilder();
                        dialog.dismiss();

                    }
                })
                .setNegativeButton(ResUtils.getStr(R.string.auto_update_dialog_negative_button), new DialogInterface.OnClickListener() {
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
     * 正在下载界面
     */
    private void downloadingBuilder() {
        pBar = new ProgressDialog(this);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle(ResUtils.getStr(R.string.auto_update_downloading));
        pBar.setMessage(ResUtils.getStr(R.string.auto_update_download_wait));
        pBar.setProgress(0);
        pBar.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        //pBar.setCancelable(false);
        pBar.show();
    }

    public class MyBinder extends Binder {

        public AutoUpdateService getService() {
            return AutoUpdateService.this;
        }
    }


}
