package com.common.download.autoupdate;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;

import com.common.R;
import com.common.bean.paramsBean.NotifyParams;
import com.common.download.DownloadManager;
import com.common.download.entity.DownloadEntry;
import com.common.download.entity.DownloadEntryDao;
import com.common.download.notify.DataWatcher;
import com.common.okhttp.OkHttpUtils;
import com.common.okhttp.callback.StringCallback;
import com.common.ui.base.main.BaseApplication;
import com.common.utils.AppUtils;
import com.common.utils.JsonUtils;
import com.common.utils.NotifyUtils;
import com.common.utils.PackageUtils;
import com.common.utils.ResUtils;
import com.common.view.dialog.widget.NormalScrollViewDialog;
import com.constants.fixed.GlobalConstants;
import com.constants.fixed.UrlConstants;
import com.constants.level.DownloadStatusLevel;

import de.greenrobot.dao.query.Query;

/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AutoUpdateService extends Service {
    PendingIntent rightPendIntent;
    NotifyUtils notify;
    NotifyParams params;
    private HomeWatcher mHomeWatcher;//home 监听
    private MyBinder myBinder = new MyBinder();
    private NormalScrollViewDialog dialog;
    private DownloadEntry entry;
    private AppAutoUpdateBean appAutoUpdateBean;
    private DataWatcher dataWatcher = new DataWatcher() {

        @Override
        public void onDataChanged(DownloadEntry data) {
            if (data.getUrl().equals(entry.getUrl())) {
                entry = data;
                updateProgress(entry);
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


        OkHttpUtils.post().url(UrlConstants.APP_VERSION_UPDATE).build().execute(new StringCallback() {
            @Override
            public void onResponse(String response) {
                appAutoUpdateBean = JsonUtils.parseToObjectBean(response, AppAutoUpdateBean.class);
                if (Integer.parseInt(appAutoUpdateBean.getVersion_code()) > AppUtils.getVersionCode(AutoUpdateService.this)) {
                    deleteDownloadHistory();
                    checkAppUpdateBuilder();
                    homeWatcher();
                }
            }
        });
    }

    /**
     * 删除上次升级下载在数据库的记录
     */
    private void deleteDownloadHistory(){
        // Query 类代表了一个可以被重复执行的查询
        Query query = BaseApplication.daoMaster.newSession().getDownloadEntryDao().queryBuilder()
                .where(DownloadEntryDao.Properties.Url.eq(appAutoUpdateBean.getUrl())).build();
        BaseApplication.daoMaster.newSession().getDownloadEntryDao().deleteInTx(query.list());
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
        dialog = new NormalScrollViewDialog(this) {
            @Override
            public void setUiBeforShow() {
                getmTvOk().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startDownloadApp();
                        notify_progress(entry);
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



    /**
     * home 键监听 用来显示隐藏升级dialog
     */
    private void homeWatcher() {
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    mHomeWatcher.stopWatch();// 要停止监听，不然会报错的。
                }
            }
            @Override
            public void onHomeLongPressed() {

            }
        });
        mHomeWatcher.startWatch();
    }

    /**
     *  通知栏列表item样式
     * @param bean
     */
    private void updateProgress(DownloadEntry bean) {
        if (null == params) {
            params = new NotifyParams();
            params.setSmallIcon(R.mipmap.ic_launcher);
            params.setPendingIntent(rightPendIntent);
            params.setTicker(ResUtils.getStr(R.string.auto_update_download_app));
            params.setTitle("Android 6.0.1 下载");
            params.setContent("正在下载中");
            params.setVibrate(false);
            params.setLights(false);
            params.setSound(false);
        }
        notify.notifyDownloadProgress(params, bean);

    }

    /*
    * 动态通知栏样式
    */
    private void notify_progress(DownloadEntry bean) {
        notify = new NotifyUtils(this, 100001);
        int smallIcon = R.mipmap.ic_launcher;
        String ticker = "您有一条新通知";
        NotifyParams params = new NotifyParams();
        params.setPendingIntent(rightPendIntent);
        params.setSmallIcon(smallIcon);
        params.setTicker(ticker);
        params.setTitle(ResUtils.getStr(R.string.auto_update_download_app));
        params.setContent("正在下载中");
        params.setVibrate(true);
        params.setLights(true);
        params.setSound(true);
        notify.notifyDownloadProgress(params, bean);
    }


    public class MyBinder extends Binder {
        public AutoUpdateService getService() {
            return AutoUpdateService.this;
        }
    }




}
