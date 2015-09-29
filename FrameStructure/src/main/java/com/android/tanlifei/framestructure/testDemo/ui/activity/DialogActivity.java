package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.common.view.prompt.BaseDialog;
import com.android.tanlifei.framestructure.common.view.prompt.DefaultDialog;
import com.android.tanlifei.framestructure.common.view.prompt.SingleDialog;
import com.android.tanlifei.framestructure.engine.interf.IDialogBtnDefaultCallBack;
import com.android.tanlifei.framestructure.engine.interf.IDialogBtnSingleCallBack;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.test_activity_dialog)
public class DialogActivity extends Activity implements IDialogBtnSingleCallBack {

    public static final String TAG = "HtppTaskActivity";


    @Click({R.id.btn_1_1,R.id.btn_1_2})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1_1:
                singleDialog(1);
                break;
            case R.id.btn_1_2:
                defaultDialog();
                break;
        }
    }



    /**
     * 单个对话框 *
     */
  /*  private void singleDialog() {
        new SingleDialog(this, new IDialogBtnSingleCallBack() {
            @Override
            public void onClickListener(BaseDialog promptDialog, View v,int callBackTag) {
                ToastUtils.show(getApplicationContext(), "rightBtn");
            }
        }).getContent().setText("确定要删除吗");
    }*/

    private void singleDialog(int callBackTag) {
        new SingleDialog(this,this,callBackTag).getContent().setText("确定要删除吗");
    }

    /**
     * 默认两个对话框 *
     */
    private void defaultDialog() {
        new DefaultDialog(this, new IDialogBtnDefaultCallBack() {
            @Override
            public void liftBtnOnClickListener(BaseDialog promptDialog, View v,int callBackTag) {
                ToastUtils.show(getApplicationContext(), "liftBtn");
            }

            @Override
            public void rightBtnOnClickListener(BaseDialog promptDialog, View v,int callBackTag) {
                promptDialog.dismiss();
                ToastUtils.show(getApplicationContext(), "rightBtn");
            }
        }).getContent().setText("确定要删除吗");
    }

    @Override
    public void onClickListener(BaseDialog promptDialog, View v, int callBackTag) {
        switch (callBackTag){
            case 1:
                ToastUtils.show(getApplicationContext(), "1");
                break;
            case 2:
                ToastUtils.show(getApplicationContext(), "2");
                break;
        }
    }
}
