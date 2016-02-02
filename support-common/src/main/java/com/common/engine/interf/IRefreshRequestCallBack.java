package com.common.engine.interf;


import com.constants.level.TaskRequestLevel;

/**
 * 提示异常点击重新刷新回调
 * <p/>
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #onRefreshRequest(TaskRequestLevel)} 刷新（点击重新请求）</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月3日 上午10:50:58
 */
public interface IRefreshRequestCallBack {

    /**
     * 刷新（点击重新请求）
     *
     * @param level 重新请求前的状态码
     */
    void onRefreshRequest(TaskRequestLevel level);
}
