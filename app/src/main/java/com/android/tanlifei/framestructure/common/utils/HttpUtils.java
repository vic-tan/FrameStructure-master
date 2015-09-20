package com.android.tanlifei.framestructure.common.utils;

import android.text.Html;

import com.android.tanlifei.framestructure.common.http.base.RequestParamBean;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Map;
import java.util.Map.Entry;


/**
 * 请求方式及参数设置
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #getClient()} 获取AsyncHttpClient对象</li>
 * <li>{@link #get(RequestParamBean, AsyncHttpResponseHandler)}  get 请求 以普通形式提交参数</li>
 * <li>{@link #getJsonParams(RequestParamBean, AsyncHttpResponseHandler)}  get 请求 以json格式提交参数</li>
 * <li>{@link #post(RequestParamBean, AsyncHttpResponseHandler)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #postJsonParams(RequestParamBean, AsyncHttpResponseHandler)} post 请求，以json格式提交参数</li>
 * <li>{@link #setMapToRequestParams(String, Map, boolean)}  把Map参数转化成 RequestParams 对象参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class HttpUtils {

    public static final String JSON_PARAMS_KEY = "json";//json参数形式提交时的key
    public final static String TAG = "AsyncHttpClient RequestParams";//日志过滤标识
    public static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象

    /**
     * 静态块，整个程序只加载一次，请求参数设置
     */
    static {
        client.setTimeout(11000); // 设置链接超时，如果不设置，默认为10s
    }

    /**
     * 获取AsyncHttpClient对象
     *
     * @return
     */
    public static AsyncHttpClient getClient() {
        return client;
    }


    // ************************************  get *****************************************//


    /**
     * get 请求 ，参数以RequestParams 形式提交
     *
     * @param params 请求参数实体
     * @param res    AsyncHttpResponseHandler 获取 byte[]数组字节
     */
    public static void get(RequestParamBean params,
                           AsyncHttpResponseHandler res) {
        client.get(params.getUrl(), setMapToRequestParams(params.getUrl(), params.getRequestParams(), false), res);
    }


    /**
     * get 请求 ，参数以Json形式提交
     *
     * @param params 请求参数实体
     * @param res    AsyncHttpResponseHandler 获取 byte[]数组字节
     */
    public static void getJsonParams(RequestParamBean params,
                                     AsyncHttpResponseHandler res) {
        client.get(params.getUrl(), setMapToRequestParams(params.getUrl(), params.getRequestParams(), true), res);
    }


    // **************************************  post *****************************************//


    /**
     * post请求  ，参数以RequestParams 形式提交
     *
     * @param params 请求参数实体
     * @param res    AsyncHttpResponseHandler 获取 byte[]数组字节
     */
    public static void post(RequestParamBean params, AsyncHttpResponseHandler res) {
        client.post(params.getUrl(), setMapToRequestParams(params.getUrl(), params.getRequestParams(), false), res);
    }

    /**
     * post提交，参数以Json形式提交
     *
     * @param params 请求参数实体
     * @param res    AsyncHttpResponseHandler 获取 byte[]数组字节
     */
    public static void postJsonParams(RequestParamBean params,
                                      AsyncHttpResponseHandler res) {
        client.post(params.getUrl(), setMapToRequestParams(params.getUrl(), params.getRequestParams(), true), res);
    }


    /**
     * 把Map参数转化成 RequestParams 对象参数
     *
     * @param url        请求路径
     * @param mapParams  map类型参数
     * @param isJsonType 是不是以json 类型提交参数
     * @return RequestParams 对象
     */
    public static RequestParams setMapToRequestParams(String url,
                                                      Map<String, Object> mapParams, boolean isJsonType) {
        RequestParams params = new RequestParams();
        StringBuffer log = new StringBuffer("url = " + url);
        log.append("<br>Parameter = [");
        if (!MapUtils.isEmpty(mapParams)) {
            for (Entry<String, Object> entry : mapParams.entrySet()) {
                log.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            if (isJsonType) {//以json 类型提交参数
                params.put(JSON_PARAMS_KEY, JsonUtils.mapToJson(mapParams));
            } else {//普通的url拼接或post参数
                for (Entry<String, Object> entry : mapParams.entrySet()) {
                    params.put(entry.getKey(), entry.getValue());
                }
            }
        }
        log.append("]");
        Logger.i(TAG, Html.fromHtml(log.toString()).toString());// 打参数日志
        return params;
    }

    // 带参数，获取json对象或者数组
    /*public static void get(String url,
                           Map<String, Object> params, JsonHttpResponseHandler res) {
        client.get(url, setMapToRequestParams(url, params), res);
    }

    // 下载数据使用，会返回byte数据
    public static void get(String url,
                           BinaryHttpResponseHandler bHandler) {
        client.get(url, bHandler);
    }


    public static void get(String url,
                           JsonHttpResponseHandler res) {
        client.get(url, res);
    }

    */


}