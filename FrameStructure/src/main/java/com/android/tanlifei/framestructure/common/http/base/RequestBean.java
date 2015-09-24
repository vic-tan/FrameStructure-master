package com.android.tanlifei.framestructure.common.http.base;

import android.content.Context;

import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatusLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.TaskLevel;
import com.android.tanlifei.framestructure.common.utils.MapUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 请求网络接口参数封装类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #RequestBean(Context, Map)} 只有请求参数构造</li>
 * <li>{@link #RequestBean(Context, Map, Map)} 即有请求求参数,又有返回参数构造</li>
 * <li>{@link #getContext()} 上下文</li>
 * <li>{@link #getRequestParams()} 获取请求参数</li>
 * <li>{@link #getReturnParams()}  获取返回参数</li>
 * <li>{@link #setReturnParams(Map)} 设置返回参数</li>
 * <li>{@link #getUrl()} 获取当前请求路径</li>
 * <li>{@link #getBaseJson()} 返回json内容的实体</li>
 * <li>{@link #setBaseJson(BaseJson)} 设置返回json内容的实体</li>
 * <li>{@link #getRequestStatusLevel()} 获取当前请求方法状态的</li>
 * <li>{@link #setRequestStatusLevel(RequestStatusLevel)}  设置当前请求方法状态的</li>
 * <li>{@link #getTaskLevel()} 获取请求时区分任务标识</li>
 * <li>{@link #setTaskLevel(TaskLevel)}  设置请求时区分任务标识</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class RequestBean {


    private final Context context;//上下文
    private final Map<String, Object> requestParams;//请求参数内容
    private Map<String, Object> returnParams;//返回参数


    /**
     * 只有请求参数构造
     * @param context
     * @param requestParams
     */
    public RequestBean(Context context, Map<String, Object> requestParams) {
        this.context = context;
        this.returnParams = new HashMap<String, Object>();
        this.requestParams = MapUtils.isNull(requestParams) ? new HashMap<String, Object>() : requestParams;
    }

    /**
     * 即有请求求参数,又有返回参数构造
     * @param context
     * @param requestParams
     * @param returnParams
     */
    public RequestBean(Context context, Map<String, Object> requestParams, Map<String, Object> returnParams) {
        this.context = context;
        this.returnParams = MapUtils.isNull(returnParams) ? new HashMap<String, Object>() : returnParams;
        this.requestParams = MapUtils.isNull(requestParams) ? new HashMap<String, Object>() : requestParams;
    }

    /**
     * 上下文
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public Map<String, Object> getRequestParams() {
        return MapUtils.isNull(requestParams) ? new HashMap<String, Object>() : requestParams;
    }

    /**
     * 获取返回参数
     *
     * @return
     */
    public Map<String, Object> getReturnParams() {
        return MapUtils.isNull(returnParams) ? new HashMap<String, Object>() : returnParams;
    }

    /**
     * 设置返回参数
     * @param returnParams
     */
    public void setReturnParams(Map<String, Object> returnParams) {
        this.returnParams = returnParams;
    }

    /**
     * 获取当前请求路径
     *
     * @return
     */
    public String getUrl() {//当前请求路径
        if (!MapUtils.isNull(getRequestParams()) && getRequestParams().containsKey(JsonConstants.JSON_TASK_URL)) {
            return (String) getRequestParams().get(JsonConstants.JSON_TASK_URL);
        }
        return "";

    }

    /**
     * 返回json内容的实体
     *
     * @return
     */
    public BaseJson getBaseJson() {//返回内容
        if (!MapUtils.isNull(getReturnParams()) && getReturnParams().containsKey(JsonConstants.JSON_BEAN)) {
            return (BaseJson) getReturnParams().get(JsonConstants.JSON_BEAN);
        }
        return new BaseJson();
    }

    /**
     * 设置json内容的实体
     *
     * @return
     */
    public void setBaseJson(BaseJson baseJson) {//返回内容
        if (!MapUtils.isNull(getReturnParams())) {
            getReturnParams().put(JsonConstants.JSON_BEAN, baseJson);
        }
    }

    /**
     * 获取当前请求方法状态的
     *
     * @return
     */
    public RequestStatusLevel getRequestStatusLevel() {//当前请求方法状态
        if (!MapUtils.isNull(getReturnParams()) && getReturnParams().containsKey(JsonConstants.JSON_REQUEST_STATUS_LEVEL)) {
            return (RequestStatusLevel) getReturnParams().get(JsonConstants.JSON_REQUEST_STATUS_LEVEL);
        }
        return RequestStatusLevel.TIMEOUT_ERROR;
    }

    /**
     * 设置当前请求方法状态的
     *
     * @return
     */
    public void setRequestStatusLevel(RequestStatusLevel level) {//当前请求方法状态
        if (!MapUtils.isNull(getReturnParams())) {
            getReturnParams().put(JsonConstants.JSON_REQUEST_STATUS_LEVEL, level);
        }
    }

    /**
     * 获取请求时区分任务标识
     *
     * @return
     */
    public TaskLevel getTaskLevel() {//多请求时区分任务标识
        if (!MapUtils.isNull(getReturnParams()) && getReturnParams().containsKey(JsonConstants.JSON_TASK_LEVEL)) {
            return (TaskLevel) getReturnParams().get(JsonConstants.JSON_TASK_LEVEL);
        }
        return TaskLevel.TASK_EMPTY;

    }

    /**
     * 设置请求时区分任务标识
     *
     * @return
     */
    public void setTaskLevel(TaskLevel level) {//多请求时区分任务标识
        if (!MapUtils.isNull(getReturnParams())) {
            getReturnParams().put(JsonConstants.JSON_TASK_LEVEL, level);
        }
    }


}
