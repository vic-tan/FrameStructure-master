package com.android.tanlifei.framestructure.testDemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.utils.InflaterUtils;
import com.android.tanlifei.framestructure.common.utils.ToastUtils;
import com.android.tanlifei.framestructure.common.view.prompt.BasePromptDialog;
import com.android.tanlifei.framestructure.common.view.prompt.DefaultPromptDialog;
import com.android.tanlifei.framestructure.common.view.prompt.SinglePromptDialog;
import com.android.tanlifei.framestructure.engine.interf.IPromptDialogBtnDefaultCallBack;
import com.android.tanlifei.framestructure.engine.interf.IPromptDialogBtnSingleCallBack;


public class DialogActivity extends Activity implements View.OnClickListener ,IPromptDialogBtnSingleCallBack{

    public static final String TAG = "HtppTaskActivity";

    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = InflaterUtils.inflater(this, R.layout.test_activity_dialog);
        setContentView(view);
        setOnClickListener();
    }


    private void setOnClickListener() {
        findViewById(R.id.btn_1_1).setOnClickListener(this);
        findViewById(R.id.btn_1_2).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
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
        new SinglePromptDialog(this, new IPromptDialogBtnSingleCallBack() {
            @Override
            public void onClickListener(BasePromptDialog promptDialog, View v,int callBackTag) {
                ToastUtils.show(getApplicationContext(), "rightBtn");
            }
        }).getContent().setText("确定要删除吗");
    }*/

    private void singleDialog(int callBackTag) {
        new SinglePromptDialog(this,this,callBackTag).getContent().setText("确定要删除吗");
    }

    /**
     * 默认两个对话框 *
     */
    private void defaultDialog() {
        new DefaultPromptDialog(this, new IPromptDialogBtnDefaultCallBack() {
            @Override
            public void liftBtnOnClickListener(BasePromptDialog promptDialog, View v,int callBackTag) {
                ToastUtils.show(getApplicationContext(), "liftBtn");
            }

            @Override
            public void rightBtnOnClickListener(BasePromptDialog promptDialog, View v,int callBackTag) {
                promptDialog.dismiss();
                ToastUtils.show(getApplicationContext(), "rightBtn");
            }
        }).getContent().setText("确定要删除吗");
    }

    @Override
    public void onClickListener(BasePromptDialog promptDialog, View v, int callBackTag) {
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
