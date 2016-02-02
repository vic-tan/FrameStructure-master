package com.common.engine.interf;

import android.view.View;

import com.common.prompt.BaseDialog;

/**
 * 提示dialog回调
 *
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #liftBtnOnClickListener(BaseDialog,View,int)} 左边按钮点击监听</li>
 * <li>{@link #rightBtnOnClickListener(BaseDialog,View,int)}右边按钮点击监听</li>
 * </ul>
 * @author tanlifei
 * @date 2015年2月3日 上午10:50:58
 */
public interface IDialogBtnDefaultCallBack {

    /**
     * 左边按钮点击监听
     * @param promptDialog 当前提示框
     * @param v 当前点击view
     * @param callBackTag 多个弹出框时区分标识
     */
    void liftBtnOnClickListener(BaseDialog promptDialog, View v,int callBackTag);

    /**
     * 右边按钮点击监听
     * @param promptDialog 当前提示框
     * @param v 当前点击view
     * @param callBackTag 多个弹出框时区分标识
     */
    void rightBtnOnClickListener(BaseDialog promptDialog, View v,int callBackTag);
}