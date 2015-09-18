package com.android.tanlifei.framestructure.common.http.base;

import android.text.Html;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.StatusConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.HttpTaskStatus;
import com.android.tanlifei.framestructure.common.constants.enumConstants.JsonLevel;
import com.android.tanlifei.framestructure.common.http.ReadLocalCustomJson;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.PhoneUtils;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.StringUtils;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;
import com.android.tanlifei.framestructure.ui.GlobalApplication;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.net.SocketTimeoutException;
import java.util.Map;


/**
 * 请求接口任务过程基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #setAsyncHttpResponseHandler(RequestBean, int, IHttpTaskCallBack)}  response响应回调</li>
 * <li>{@link #sendHandler(CallbackBean, IHttpTaskCallBack)}  发送回调</li>
 * <li>{@link #replaceId(String)} 把json 中的"id" key  替换成"my_id" key ,这样做是为了跟 litepal 或GreenDao 等关系型数据库库自带的id冲突</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class BaseHttpTask {


    protected static final String TAG = "HttpTask task";//日志过滤标识


    /**
     * response响应回调
     *
     * @param callBackMethod
     * @return
     */
    protected static AsyncHttpResponseHandler setAsyncHttpResponseHandler(final RequestBean params, final int callBackTag, final IHttpTaskCallBack callBackMethod) {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onFinish() {
                super.onFinish();
                log("--------------> onFinish()");
                sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.FINISH, callBackTag, params.getCallbackParams()), callBackMethod);
            }

            @Override
            public void onCancel() {
                super.onCancel();
                log("--------------> onCancel()");
                sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.CANCEL, callBackTag, params.getCallbackParams()), callBackMethod);
            }

            @Override
            public void onStart() {
                super.onStart();
                log("--------------> onStart()");
                sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.START, callBackTag, params.getCallbackParams()), callBackMethod);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.PROGRESS, callBackTag, params.getCallbackParams()), callBackMethod);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                try {
                    if (StatusConstants.JSON_LEVEL == JsonLevel.FULL) {//开启请求接口成功读取对应的本的的自定义JSON
                        ReadLocalCustomJson.readJson(params, callBackMethod, callBackTag);
                        return;
                    }
                    BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(responseBody)), BaseJson.class);
                    if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_SUCCEE)) {// 请求成功
                        log("" + replaceId(new String(responseBody)));
                        sendHandler(new CallbackBean(jsonBean, HttpTaskStatus.SUCCESS, callBackTag, params.getCallbackParams()), callBackMethod);
                    } else {// 服务错误
                        log("--------------> service error (onSuccess)");
                        sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.SERVICE_ERROR, callBackTag, params.getCallbackParams()), callBackMethod);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log(Html.fromHtml("--------------> Exception (onSuccess)<br>" + e.toString()).toString());
                    sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.SERVICE_ERROR, callBackTag, params.getCallbackParams()), callBackMethod);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (error instanceof SocketTimeoutException) {
                    log(Html.fromHtml("--------------> onFailure()<br>" + error.toString()).toString());
                    sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.TIMEOUT_ERROR, callBackTag, params.getCallbackParams()), callBackMethod);
                } else {
                    log(Html.fromHtml("--------------> onFailure()<br>" + error.toString()).toString());
                    sendHandler(new CallbackBean(new BaseJson(), HttpTaskStatus.FAILURE, callBackTag, params.getCallbackParams()), callBackMethod);
                }
            }
        };
    }

    protected static void log(String log) {
        if (!StringUtils.isEmpty(log)) {
            Logger.i(TAG, "" + log);
        }
    }


    /**
     * 发送 Handler
     *
     * @param handlerBean
     * @param callBack
     */
    protected static void sendHandler(CallbackBean handlerBean, IHttpTaskCallBack callBack) {
        callBack.taskHandler(handlerBean);
    }

    /**
     * 网络错误提示
     *
     * @param callBackMethod
     * @param callBackTag
     */
    protected static boolean networkError(IHttpTaskCallBack callBackMethod, int callBackTag, Map<String, Object> backParams) {
        if (!PhoneUtils.isNetworkOk(GlobalApplication.appContext)) {
            log("--------------> " + ResUtils.getStr(R.string.common_prompt_network));
            sendHandler(new CallbackBean(HttpTaskStatus.NETWORK_ERROR, callBackTag, backParams), callBackMethod);
            return true;
        } else
            return false;

    }


    /**
     * 把json 中的"id" key  替换成"my_id" key ,这样做是为了跟 litepal 或GreenDao 等关系型数据库库自带的id冲突
     *
     * @param responseBody
     * @return
     */
    protected static String replaceId(String responseBody) {
        if (StringUtils.isEmpty(responseBody)) {
            return responseBody;
        }
        return responseBody.replace("\"id\":", "\"my_id\":");
    }

}
