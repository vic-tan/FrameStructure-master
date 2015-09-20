package com.android.tanlifei.framestructure.common.http;

import com.android.tanlifei.framestructure.common.constants.enumConstants.TaskLevel;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpTask;
import com.android.tanlifei.framestructure.common.http.base.RequestBean;
import com.android.tanlifei.framestructure.common.utils.HttpUtils;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(RequestBean, IHttpTaskCallBack)}  get 请求 以普通形式提交参数</li>
 * <li>{@link #get(RequestBean, TaskLevel, IHttpTaskCallBack)}  get 请求 以普通形式提交参数</li>
 * <li>{@link #getByJsonParams(RequestBean, IHttpTaskCallBack)}  get 请求 以json格式提交参数</li>
 * <li>{@link #getByJsonParams(RequestBean, TaskLevel, IHttpTaskCallBack)}  get 请求 以json格式提交参数</li>
 * <li>{@link #post(RequestBean, TaskLevel, IHttpTaskCallBack)}   post 请求，以普通形式提交参数</li>
 * <li>{@link #post(RequestBean, IHttpTaskCallBack)}   post 请求，以普通形式提交参数</li>
 * <li>{@link #postByJsonParams(RequestBean, TaskLevel, IHttpTaskCallBack)}  post 请求，以json格式提交参数</li>
 * <li>{@link #postByJsonParams(RequestBean, IHttpTaskCallBack)}  post 请求，以json格式提交参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class HttpTask extends BaseHttpTask {


    /**
     * get 请求 以普通形式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param level          多个请求任务区分标识
     */
    public static void get(RequestBean params, TaskLevel level, IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, level, params.getCallbackParams())) {
            HttpUtils.get(params, setAsyncHttpResponseHandler(params, level, callBackMethod));
        }

    }

    /**
     * get 请求 以普通形式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     */
    public static void get(RequestBean params, IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, TaskLevel.TASK_EMPTY, params.getCallbackParams())) {
            HttpUtils.get(params, setAsyncHttpResponseHandler(params, TaskLevel.TASK_EMPTY, callBackMethod));
        }

    }


    /**
     * get 请求 以json格式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param level          多个请求任务区分标识
     */
    public static void getByJsonParams(RequestBean params, TaskLevel level,
                                       IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, level, params.getCallbackParams())) {
            HttpUtils.getJsonParams(params, setAsyncHttpResponseHandler(params, level, callBackMethod));
        }
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     */
    public static void getByJsonParams(RequestBean params,
                                       IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, TaskLevel.TASK_EMPTY, params.getCallbackParams())) {
            HttpUtils.getJsonParams(params, setAsyncHttpResponseHandler(params, TaskLevel.TASK_EMPTY, callBackMethod));
        }
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param level          多个请求任务区分标识
     */
    public static void post(RequestBean params, TaskLevel level,
                            IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, level, params.getCallbackParams())) {
            HttpUtils.post(params, setAsyncHttpResponseHandler(params, level, callBackMethod));
        }
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     */
    public static void post(RequestBean params,
                            IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, TaskLevel.TASK_EMPTY, params.getCallbackParams())) {
            HttpUtils.post(params, setAsyncHttpResponseHandler(params, TaskLevel.TASK_EMPTY, callBackMethod));
        }
    }

    /**
     * post 请求，以json格式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param level          多个请求任务区分标识
     */
    public static void postByJsonParams(RequestBean params, TaskLevel level,
                                        IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, level, params.getCallbackParams())) {
            HttpUtils.postJsonParams(params, setAsyncHttpResponseHandler(params, level, callBackMethod));
        }
    }

    /**
     * post 请求，以json格式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     */
    public static void postByJsonParams(RequestBean params,
                                        IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, TaskLevel.TASK_EMPTY, params.getCallbackParams())) {
            HttpUtils.postJsonParams(params, setAsyncHttpResponseHandler(params, TaskLevel.TASK_EMPTY, callBackMethod));
        }
    }
}
