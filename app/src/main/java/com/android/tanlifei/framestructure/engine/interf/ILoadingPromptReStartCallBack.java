package com.android.tanlifei.framestructure.engine.interf;

import android.content.Context;

import com.android.tanlifei.framestructure.common.constants.enumConstants.PromptStatus;

/**
 * 提示异常点击重新刷新回调
 *
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #onRefresh(PromptStatus)} 刷新（点击重新请求）</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月3日 上午10:50:58
 */
public interface ILoadingPromptReStartCallBack {

    /**
     * 刷新（点击重新请求）
     * @param status 重新请求前的状态码
     */
    void onRefresh(PromptStatus status);
}
