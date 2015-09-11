package com.android.tanlifei.framestructure.common.http;

import android.os.Handler;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.common.constants.enumConstants.HttpTaskStatus;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpTask;
import com.android.tanlifei.framestructure.common.utils.HttpUtils;
import com.android.tanlifei.framestructure.common.utils.PhoneUtils;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.ui.GlobalApplication;

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
public class HttpTask extends BaseHttpTask {


    /**
     * get 请求 以普通形式提交参数
     *
     * @param url     请求路径
     * @param params  请求参数
     * @param handler
     */
    public static void get(String url, Map<String, Object> params,
                           Handler handler) {
        if (!PhoneUtils.isNetworkOk(GlobalApplication.appContext)) {
            sendHandler(handler, HttpTaskStatus.NETWORK_ERROR.value(), ResUtils.getStr(R.string.common_prompt_network));
            return;
        }
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
        if (!PhoneUtils.isNetworkOk(GlobalApplication.appContext)) {
            sendHandler(handler, HttpTaskStatus.NETWORK_ERROR.value(), ResUtils.getStr(R.string.common_prompt_network));
            return;
        }
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
        if (!PhoneUtils.isNetworkOk(GlobalApplication.appContext)) {
            sendHandler(handler, HttpTaskStatus.NETWORK_ERROR.value(), ResUtils.getStr(R.string.common_prompt_network));
            return;
        }
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
        if (!PhoneUtils.isNetworkOk(GlobalApplication.appContext)) {
            sendHandler(handler, HttpTaskStatus.NETWORK_ERROR.value(), ResUtils.getStr(R.string.common_prompt_network));
            return;
        }
        HttpUtils.postJsonParams(url, params, setAsyncHttpResponseHandler(url, handler));
    }
}
