package com.common.engine.interf;


/**
 * 提示异常点击重新刷新回调
 * <p/>
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #onRefreshRequest()} 刷新（点击重新请求）</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月3日 上午10:50:58
 */
public interface IRefreshRequestCallBack {

    /**
     * 刷新（点击重新请求）
     */
    void onRefreshRequest();
}
