package com.android.tanlifei.framestructure.common.http.base;

import com.android.tanlifei.framestructure.common.utils.MapUtils;
import com.android.tanlifei.framestructure.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求网络接口参数封装类
 * Created by tanlifei on 15/9/18.
 */
public class TaskParamsBean {

    private final String url;//请求的url
    private final Map<String, Object> requestParams;//返回内容
    private final Map<String, Object> callbackParams;//多请求时区分任务标识

    public TaskParamsBean(String url, Map<String, Object> requestParams, Map<String, Object> backParams) {
        this.callbackParams = MapUtils.isEmpty(backParams) ? new HashMap<String, Object>() : backParams;
        this.url = StringUtils.isEmpty(url) ? "" : url;
        this.requestParams = MapUtils.isEmpty(requestParams) ? new HashMap<String, Object>() : requestParams;
    }

    public Map<String, Object> getCallbackParams() {
        return callbackParams;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public String getUrl() {
        return url;
    }
}
