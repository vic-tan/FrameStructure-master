package com.android.tanlifei.framestructure.common.http;

import com.android.tanlifei.framestructure.common.http.base.BaseHttpTask;
import com.android.tanlifei.framestructure.common.http.base.RequestBean;
import com.android.tanlifei.framestructure.common.utils.HttpUtils;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(RequestBean, IHttpTaskCallBack)}  get 请求 以普通形式提交参数</li>
 * <li>{@link #get(RequestBean, int, IHttpTaskCallBack)}  get 请求 以普通形式提交参数</li>
 * <li>{@link #getByJsonParams(RequestBean, IHttpTaskCallBack)}  get 请求 以json格式提交参数</li>
 * <li>{@link #getByJsonParams(RequestBean, int, IHttpTaskCallBack)}  get 请求 以json格式提交参数</li>
 * <li>{@link #post(RequestBean, int, IHttpTaskCallBack)}   post 请求，以普通形式提交参数</li>
 * <li>{@link #post(RequestBean, IHttpTaskCallBack)}   post 请求，以普通形式提交参数</li>
 * <li>{@link #postByJsonParams(RequestBean, int, IHttpTaskCallBack)}  post 请求，以json格式提交参数</li>
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
     * @param callBackTag    多个请求任务区分标识
     */
    public static void get(RequestBean params, int callBackTag, IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, callBackTag, params.getCallbackParams())) {
            HttpUtils.get(params, setAsyncHttpResponseHandler(params, callBackTag, callBackMethod));
        }

    }

    /**
     * get 请求 以普通形式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     */
    public static void get(RequestBean params, IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, -1, params.getCallbackParams())) {
            HttpUtils.get(params, setAsyncHttpResponseHandler(params, -1, callBackMethod));
        }

    }


    /**
     * get 请求 以json格式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param callBackTag    多个请求任务区分标识
     */
    public static void getByJsonParams(RequestBean params, int callBackTag,
                                       IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, callBackTag, params.getCallbackParams())) {
            HttpUtils.getJsonParams(params, setAsyncHttpResponseHandler(params, callBackTag, callBackMethod));
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
        if (!networkError(callBackMethod, -1, params.getCallbackParams())) {
            HttpUtils.getJsonParams(params, setAsyncHttpResponseHandler(params, -1, callBackMethod));
        }
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param callBackTag    多个请求任务区分标识
     */
    public static void post(RequestBean params, int callBackTag,
                            IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, callBackTag, params.getCallbackParams())) {
            HttpUtils.post(params, setAsyncHttpResponseHandler(params, callBackTag, callBackMethod));
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
        if (!networkError(callBackMethod, -1, params.getCallbackParams())) {
            HttpUtils.post(params, setAsyncHttpResponseHandler(params, -1, callBackMethod));
        }
    }

    /**
     * post 请求，以json格式提交参数
     *
     * @param params         请求参数实体
     * @param callBackMethod 回调方法
     * @param callBackTag    多个请求任务区分标识
     */
    public static void postByJsonParams(RequestBean params, int callBackTag,
                                        IHttpTaskCallBack callBackMethod) {
        if (!networkError(callBackMethod, callBackTag, params.getCallbackParams())) {
            HttpUtils.postJsonParams(params, setAsyncHttpResponseHandler(params, callBackTag, callBackMethod));
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
        if (!networkError(callBackMethod, -1, params.getCallbackParams())) {
            HttpUtils.postJsonParams(params, setAsyncHttpResponseHandler(params, -1, callBackMethod));
        }
    }
}
