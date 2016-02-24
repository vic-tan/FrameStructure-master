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
import com.common.utils.PackageUtils;
import com.constants.fixed.GlobalConstants;
import com.constants.level.DownloadStatusLevel;

/**
 * app 版本升级
 * Created by tanlifei on 16/2/22.
 */
public class AutoUpdateService extends Service {
    private final String url = "http://p.gdown.baidu.com/34732ea3d557058eacd7b8c279cf45d0a180e711b46e2754c515a2ea87e3e72930c95bd008a8336d6f16a60c1722954818365ddd0b55e8b7d5c9a170c565eb4af0bd1bffed4bef471337b4f8f8b0dd4da8cfc96d00d28ab7bba89a555b7478fb1749eb173274951f4e0e6cf0d710554789cbb77f0f35beb155017486a19af1867c7cfc79c7d54d1352651f73c5d0e634ffab0e94ebf345a5c5cc013c678785f5c8aabbc56d7bb3ec3d4da4e8eae0957589b29b1d77f6baead97d8fe7e2e50dab52ca10f52bea310751f518be645f68caf29c77aadc9755756df46cb4b979cced3c8fed50b11df695";
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
                    if (null != pBar && pBar.isShowing())
                        pBar.dismiss();
                    PackageUtils.installNormal(AutoUpdateService.this, entry.getSaveUrl());
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
        entry.setName("灵犀语音助手.apk");
        entry.setSaveUrl(GlobalConstants.DOWNLOAD_PATH + entry.getName());
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
