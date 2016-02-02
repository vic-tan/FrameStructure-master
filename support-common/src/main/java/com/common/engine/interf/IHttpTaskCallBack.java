package com.common.engine.interf;


import com.common.http.base.RequestBean;

/**
 * 请求网络接口，正在请求加载提示成功后回调
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #taskCallBack(RequestBean)}  请求接口完成后的回调方法集体处理</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public interface IHttpTaskCallBack {
    /**
     * 请求接口完成后的回调方法集体处理
     *
     * @param requestBean 返回数据
     */
    void taskCallBack(RequestBean requestBean);
}
