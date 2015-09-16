package com.android.tanlifei.framestructure.common.view.prompt;

import android.content.Context;
import android.view.View;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.engine.interf.IPromptDialogBtnDefaultCallBack;
import com.android.tanlifei.framestructure.engine.interf.IPromptDialogBtnSingleCallBack;


/**
 * 加载数据时数据错误,或为空时view
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #DefaultPromptDialog(Context,IPromptDialogBtnDefaultCallBack)}  创建对话框全屏宽高</li>
 * <li>{@link #DefaultPromptDialog(Context,IPromptDialogBtnDefaultCallBack,int)} 创建可以区分多个对话框标识全屏宽高</li>
 * <li>{@link #liftBtnClickListener(View,int)} 左按钮事件监听</li>
 * <li>{@link #rightBtnClickListener(View,int)} 右按钮事件监听</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class DefaultPromptDialog extends BasePromptDialog {


    private IPromptDialogBtnDefaultCallBack callBack;



    /**
     * 创建对话框全屏宽高
     *
     * @param context
     * @param callBack 回调接口
     */
    public DefaultPromptDialog(Context context ,IPromptDialogBtnDefaultCallBack callBack) {
        super(context, R.layout.common_prompt_dialog_default_view);
        this.callBack = callBack;
    }


    /**
     * 创建可以区分多个对话框标识全屏宽高
     *
     * @param context
     * @param callBackTag 多个弹出框时区分标识
     * @param callBack 回调接口
     */
    public DefaultPromptDialog(Context context,IPromptDialogBtnDefaultCallBack callBack,int callBackTag) {
        super(context, R.layout.common_prompt_dialog_default_view,callBackTag);
        this.callBack = callBack;
    }

    /**
     * 左按钮事件监听
     * @param v
     * @param callBackTag 多个弹出框时区分标识
     */
    @Override
    public void liftBtnClickListener(View v,int callBackTag) {
        callBack.liftBtnOnClickListener(this, v,callBackTag);
    }

    /**
     * 右按钮事件监听
     * @param v
     * @param callBackTag 多个弹出框时区分标识
     */
    @Override
    public void rightBtnClickListener(View v,int callBackTag) {
        callBack.rightBtnOnClickListener(this, v,callBackTag);
    }




}
