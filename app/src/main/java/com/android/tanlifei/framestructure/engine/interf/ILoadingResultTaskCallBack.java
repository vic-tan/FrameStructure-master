package com.android.tanlifei.framestructure.engine.interf;

import com.android.tanlifei.framestructure.bean.base.BaseJson;

import java.util.Map;


/**
 * 请求网络接口，正在请求加载提示成功后回调
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public interface ILoadingResultTaskCallBack {

    /**
     * 请求接口完成要做的处理
     *
     * @param params   请求接口前携带的参数
     * @param baseJson 返回的json数据
     * @param taskTag  请求接口任务标识
     */
    void resultTask(Map<String, Object> params, BaseJson baseJson, int taskTag);
}
