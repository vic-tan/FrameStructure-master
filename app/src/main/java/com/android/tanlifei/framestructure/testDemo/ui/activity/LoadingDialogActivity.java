package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.bigkoo.svprogresshud.SVProgressHUD;

public class LoadingDialogActivity extends Activity {

    int progress = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 5;
            if (SVProgressHUD.getProgressBar(LoadingDialogActivity.this).getMax() != SVProgressHUD.getProgressBar(LoadingDialogActivity.this).getProgress()) {
                SVProgressHUD.getProgressBar(LoadingDialogActivity.this).setProgress(progress);
                SVProgressHUD.setText(LoadingDialogActivity.this, "进度 " + progress + "%");

                mHandler.sendEmptyMessageDelayed(0, 500);
            } else {
                SVProgressHUD.dismiss(LoadingDialogActivity.this);
                SVProgressHUD.getProgressBar(LoadingDialogActivity.this).setProgress(0);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_loading_dialog);
    }

    public void show(View view) {
        SVProgressHUD.show(this);
    }

    public void showWithMaskType(View view) {
        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.None);
        //       SVProgressHUD.showWithMaskType(this,SVProgressHUD.SVProgressHUDMaskType.Black);
        //   SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.Clear);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.ClearCancel);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.Gradient);
//        SVProgressHUD.showWithMaskType(this, SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    public void showWithStatus(View view) {
        SVProgressHUD.showWithStatus(this, "加载中...");
    }

    public void showInfoWithStatus(View view) {
        SVProgressHUD.showInfoWithStatus(this, "这是提示", SVProgressHUD.SVProgressHUDMaskType.None);
    }

    public void showSuccessWithStatus(View view) {
        SVProgressHUD.showSuccessWithStatus(this, "恭喜，提交成功！");
    }

    public void showErrorWithStatus(View view) {
        SVProgressHUD.showErrorWithStatus(this, "不约，叔叔我们不约～", SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }

    public void showWithProgress(View view) {
        SVProgressHUD.showWithProgress(this, "进度 " + progress + "%", SVProgressHUD.SVProgressHUDMaskType.Black);
        progress = 0;
        mHandler.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (SVProgressHUD.isShowing(this)) {
                SVProgressHUD.dismiss(this);
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }

}
