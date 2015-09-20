package com.android.tanlifei.framestructure.common.http.base;

import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatusLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.TaskLevel;
import com.android.tanlifei.framestructure.common.utils.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求网络接口发送回调封装类
 * Created by tanlifei on 15/9/18.
 */


public class CallbackBean {

    private final RequestStatusLevel status;//当前请求方法状态
    private final BaseJson baseJson;//返回内容
    private final Map<String, Object> params;//多请求时区分任务标识
    private final TaskLevel level;//多请求时区分任务标识

    public CallbackBean(BaseJson baseJson, RequestStatusLevel status, TaskLevel taskTag, Map<String, Object> backParams) {
        this.baseJson = baseJson;
        this.status = status;
        this.params = MapUtils.isEmpty(backParams) ? new HashMap<String, Object>() : backParams;
        this.level = taskTag;
    }

    public CallbackBean(RequestStatusLevel status, TaskLevel taskTag, Map<String, Object> backParams) {
        baseJson = null;
        this.status = status;
        this.params = MapUtils.isEmpty(backParams) ? new HashMap<String, Object>() : backParams;
        this.level = taskTag;
    }

    public BaseJson getBaseJson() {
        return baseJson;
    }

    public TaskLevel getLevel() {
        return level;
    }

    public RequestStatusLevel getStatus() {
        return status;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
