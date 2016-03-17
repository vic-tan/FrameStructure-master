package com.example.demo.frame.activity;

import android.os.Bundle;
import android.view.View;

import com.common.engine.interf.IDialogBtnDefaultCallBack;
import com.common.engine.interf.IDialogBtnSingleCallBack;
import com.common.prompt.BaseDialog;
import com.common.prompt.DefaultDialog;
import com.common.prompt.SingleDialog;
import com.common.ui.base.activity.BaseActivity;
import com.common.utils.ToastUtils;
import com.common.utils.ViewFindUtils;
import com.example.demo.R;


public class Demo_DialogActivity extends BaseActivity implements IDialogBtnSingleCallBack, View.OnClickListener {

    public static final String TAG = "TestHtppTaskActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_dialog);
        ViewFindUtils.find(this, R.id.btn_1_1).setOnClickListener(this);
        ViewFindUtils.find(this, R.id.btn_1_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1_1) {
            singleDialog(1);

        } else if (v.getId() == R.id.btn_1_2) {
            defaultDialog();

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
        new SingleDialog(this, this, callBackTag).getContent().setText("确定要删除吗");
    }

    /**
     * 默认两个对话框 *
     */
    private void defaultDialog() {
        new DefaultDialog(this, new IDialogBtnDefaultCallBack() {
            @Override
            public void liftBtnOnClickListener(BaseDialog promptDialog, View v, int callBackTag) {
                ToastUtils.show( "liftBtn");
            }

            @Override
            public void rightBtnOnClickListener(BaseDialog promptDialog, View v, int callBackTag) {
                promptDialog.dismiss();
                ToastUtils.show( "rightBtn");
            }
        }).getContent().setText("确定要删除吗");
    }

    @Override
    public void onClickListener(BaseDialog promptDialog, View v, int callBackTag) {
        switch (callBackTag) {
            case 1:
                ToastUtils.show("1");
                break;
            case 2:
                ToastUtils.show( "2");
                break;
        }
    }

    @Override
    protected String setActionBarTitle() {
        return "Dialog";
    }
}
