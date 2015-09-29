package com.android.tanlifei.framestructure.engine.interf;

import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatusLevel;

/**
 * 提示异常点击重新刷新回调
 * <p/>
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #onRefresh(RequestStatusLevel)} 刷新（点击重新请求）</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月3日 上午10:50:58
 */
public interface ILayoutReStartCallBack {

    /**
     * 刷新（点击重新请求）
     *
     * @param level 重新请求前的状态码
     */
    void onRefresh(RequestStatusLevel level);
}
