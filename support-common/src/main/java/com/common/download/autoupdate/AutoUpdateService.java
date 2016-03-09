package com.common.download.autoupdate;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;

import com.common.download.DownloadManager;
import com.common.download.entity.DownloadEntry;
import com.common.download.notify.DataWatcher;
import com.common.engine.interf.IHttpTaskCallBack;
import com.common.http.base.BaseHttpParams;
import com.common.http.base.RequestBean;
import com.common.http.task.HttpTask;
import com.common.notify.NotifyManager;
import com.common.utils.AppUtils;
import com.common.utils.JsonUtils;
import com.common.utils.PackageUtils;
import com.constants.fixed.GlobalConstants;
import com.constants.fixed.UrlConstants;
import com.constants.level.DownloadStatusLevel;

/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AutoUpdateService extends Service {
    //ProgressDialog pBar;
    private MyBinder myBinder = new MyBinder();
    private DownloadEntry entry;
    private AppAutoUpdateBean appAutoUpdateBean;
    private DataWatcher dataWatcher = new DataWatcher() {

        @Override
        public void onDataChanged(DownloadEntry data) {
            if (data.getUrl().equals(entry.getUrl())) {
                entry = data;
                NotifyManager.getInstance(AutoUpdateService.this).progressNotify(entry);
                if (entry.getStatus() == DownloadStatusLevel.DONE.value()) {//下载完成
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
        DownloadManager.getInstance(this).addObserver(dataWatcher);
        entry = new DownloadEntry(appAutoUpdateBean.getUrl());
        entry.setName(appAutoUpdateBean.getName());
        entry.setSaveUrl(GlobalConstants.DOWNLOAD_PATH + entry.getName());
        DownloadManager.getInstance(this).add(entry);
    }


    /**
     * 升级提示框
     */
    private void checkAppUpdateBuilder() {
        final FoundNewVersionDialog dialog = new FoundNewVersionDialog(this) {
            @Override
            public void setUiBeforShow() {
                getmTvOk().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startDownloadApp();
                        //downloadingBuilder();
                       /* NotifyBean notifyBean = new NotifyBean();
                        notifyBean.setIconId(R.mipmap.ic_launcher);
                        notifyBean.setContent("aaa");
                        notifyBean.setTitle("下载");
                        notifyBean.setNotifyId(1000);*/
                        NotifyManager.getInstance(AutoUpdateService.this).progressNotify(entry);
                        dismiss();
                    }
                });

                getmTvExit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        };
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        dialog.getmTvContent().setText(Html.fromHtml(appAutoUpdateBean.getDesc()).toString());
        dialog.setCanceledOnTouchOutside(false);
    }




    public class MyBinder extends Binder {

        public AutoUpdateService getService() {
            return AutoUpdateService.this;
        }
    }


}
