package com.android.tanlifei.framestructure.common;

import android.annotation.SuppressLint;

import com.android.tanlifei.framestructure.common.constants.StatusConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.OnOffLevel;
import com.android.tanlifei.framestructure.common.http.ReadLocalCustomJson;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.MapUtils;
import com.loopj.android.http.HttpGet;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 单元测试专用测试网络请求类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #get(String, Map)}  get 请求 以普通形式提交参数</li>
 * <li>{@link #getJsonParams(String, Map)}  get 请求 以json格式提交参数</li>
 * <li>{@link #post(String, Map)}  post 请求，以普通形式提交参数</li>
 * <li>{@link #postJsonParams(String, Map)} post 请求，以json格式提交参数</li>
 * <li>{@link #task(String, HttpEntityEnclosingRequestBase)}  请求结果 </li>
 * <li>{@link #setMapToRequestParams(String, Map, boolean)}  把Map参数转化成 RequestParams 对象参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
@SuppressLint("NewApi")
public class TestHttpUtils {

    public static final String JSON_PARAMS_KEY = "json";//json参数形式提交时的key

    /**
     * get 请求 普通参数提交
     */
    public static String get(String url, Map<String, Object> params) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setEntity(new UrlEncodedFormEntity(setMapToRequestParams(url,
                params, false), HTTP.UTF_8));
        return task(url, httpGet);
    }

    /**
     * get 请求 json参数提交
     */
    public static String getJsonParams(String url, Map<String, Object> params) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setEntity(new UrlEncodedFormEntity(setMapToRequestParams(url,
                params, true), HTTP.UTF_8));
        return task(url, httpGet);
    }


    /**
     * post 请求 普通参数提交
     */
    public static String post(String url, Map<String, Object> params)
            throws Exception {
        HttpPost httpRequest = new HttpPost(url); // 建立HTTP POST联机
        httpRequest.setEntity(new UrlEncodedFormEntity(setMapToRequestParams(url,
                params, false), HTTP.UTF_8)); // 发出http请求
        return task(url, httpRequest);
    }

    /**
     * post 请求 json参数提交
     */
    public static String postJsonParams(String url, Map<String, Object> params)
            throws Exception {
        HttpPost httpRequest = new HttpPost(url); // 建立HTTP POST联机
        httpRequest.setEntity(new UrlEncodedFormEntity(setMapToRequestParams(url,
                params, true), HTTP.UTF_8)); // 发出http请求
        return task(url, httpRequest);
    }


    /**
     * 请求结果
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    private static String task(String url, HttpEntityEnclosingRequestBase httpRequest) throws IOException {
        HttpResponse httpResponse = new DefaultHttpClient()
                .execute(httpRequest);// 发出http请求
        String result = EntityUtils.toString(httpResponse.getEntity()); // 获取字符串
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            if (StatusConstants.JSON_LEVEL == OnOffLevel.FULL) {//开启请求接口成功读取对应的本的的自定义JSON
                result = ReadLocalCustomJson.readJson(url);
            }
            Logger.i(TestConstants.TAG, result + "");
        } else {
            Logger.e(TestConstants.TAG, "--->请求接口失败");
        }
        return result;
    }


    /**
     * 打印参数 *
     */
    public static List<NameValuePair> setMapToRequestParams(String url,
                                                            Map<String, Object> mapParams, boolean isJsonType) {
        List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
        StringBuffer log = new StringBuffer("url = " + url);
        log.append(",Parameter = [");
        if (!MapUtils.isEmpty(mapParams)) {
            for (Entry<String, Object> entry : mapParams.entrySet()) {
                log.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            if (isJsonType) {//以json 类型提交参数
                httpParams.add(new BasicNameValuePair(JSON_PARAMS_KEY, JsonUtils
                        .mapToJson(mapParams)));
            } else {//普通的url拼接或post参数
                for (Entry<String, Object> entry : mapParams.entrySet()) {
                    httpParams.add(new BasicNameValuePair(entry.getKey() + "", entry.getValue() + ""));
                }
            }
        }
        log.append("]");
        Logger.i(TestConstants.TAG, log.toString());// 打参数日志
        return httpParams;
    }
}