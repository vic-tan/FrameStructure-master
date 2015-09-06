package com.android.tanlifei.framestructure.common.http;

import android.os.Handler;

import com.android.tanlifei.framestructure.common.http.base.BaseRequestTask;
import com.android.tanlifei.framestructure.common.utils.HttpUtils;

import java.util.Map;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(String, Map, Handler)} get 请求 以普通形式提交参数</li>
 * <li>{@link #getByJsonParams(String, Map, Handler)} get 请求 以json格式提交参数</li>
 * <li>{@link #post(String, Map, Handler)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #postByJsonParams(String, Map, Handler)} post 请求，以json格式提交参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class RequestTask extends BaseRequestTask {


    /**
     * get 请求 以普通形式提交参数
     *
     * @param url     请求路径
     * @param params  请求参数
     * @param handler
     */
    public static void get(String url, Map<String, Object> params,
                           Handler handler) {
        HttpUtils.get(url, params, setAsyncHttpResponseHandler(url, handler));
    }

    /**
     * get 请求 以json格式提交参数
     *
     * @param url     请求路径
     * @param params  请求参数
     * @param handler
     */
    public static void getByJsonParams(String url, Map<String, Object> params,
                                       Handler handler) {
        HttpUtils.getJsonParams(url, params, setAsyncHttpResponseHandler(url, handler));
    }

    /**
     * post 请求，以普通形式提交参数
     *
     * @param url     请求路径
     * @param params  请求参数
     * @param handler
     */
    public static void post(String url, Map<String, Object> params,
                            Handler handler) {
        HttpUtils.post(url, params, setAsyncHttpResponseHandler(url, handler));
    }

    /**
     * post 请求，以json格式提交参数
     *
     * @param url     请求路径
     * @param params  请求参数
     * @param handler
     */
    public static void postByJsonParams(String url, Map<String, Object> params,
                                        Handler handler) {
        HttpUtils.postJsonParams(url, params, setAsyncHttpResponseHandler(url, handler));
    }
}
