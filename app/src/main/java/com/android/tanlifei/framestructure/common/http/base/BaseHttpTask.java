package com.android.tanlifei.framestructure.common.http.base;

import android.text.Html;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.StatusConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatusLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.OnOffLevel;
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


/**
 * 请求接口任务过程基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #setAsyncHttpResponseHandler(TaskBean, IHttpTaskCallBack)}  response响应回调</li>
 * <li>{@link #sendHandler(TaskBean, IHttpTaskCallBack)}  发送回调</li>
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
    protected static AsyncHttpResponseHandler setAsyncHttpResponseHandler(final TaskBean params, final IHttpTaskCallBack callBackMethod) {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onFinish() {
                super.onFinish();
                log("--------------> onFinish()");
                params.setRequestStatusLevel(RequestStatusLevel.FINISH);
                sendHandler(params, callBackMethod);
            }

            @Override
            public void onCancel() {
                super.onCancel();
                log("--------------> onCancel()");
                params.setRequestStatusLevel(RequestStatusLevel.CANCEL);
                sendHandler(params, callBackMethod);
            }

            @Override
            public void onStart() {
                super.onStart();
                log("--------------> onStart()");
                params.setRequestStatusLevel(RequestStatusLevel.START);
                sendHandler(params, callBackMethod);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                params.setRequestStatusLevel(RequestStatusLevel.PROGRESS);
                sendHandler(params, callBackMethod);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                try {
                    if (StatusConstants.JSON_LEVEL == OnOffLevel.FULL) {//开启请求接口成功读取对应的本的的自定义JSON
                        ReadLocalCustomJson.readJson(params, callBackMethod);
                        return;
                    }
                    BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(responseBody)), BaseJson.class);
                    if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_SUCCEE)) {// 请求成功
                        log("" + replaceId(new String(responseBody)));
                        log(JsonUtils.format(replaceId(new String(responseBody))).toString());
                        if(StringUtils.isEmpty(jsonBean.getData())){
                            params.setRequestStatusLevel(RequestStatusLevel.EMPTY_DATA);
                            params.setBaseJson(jsonBean);
                            sendHandler(params, callBackMethod);
                        }else {
                            params.setRequestStatusLevel(RequestStatusLevel.SUCCESS);
                            params.setBaseJson(jsonBean);
                            sendHandler(params, callBackMethod);
                        }
                    } else {// 服务错误
                        log("--------------> service error (onSuccess)");
                        params.setRequestStatusLevel(RequestStatusLevel.SERVICE_ERROR);
                        sendHandler(params, callBackMethod);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log(Html.fromHtml("--------------> Exception (onSuccess)<br>" + e.toString()).toString());
                    params.setRequestStatusLevel(RequestStatusLevel.SERVICE_ERROR);
                    sendHandler(params, callBackMethod);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (error instanceof SocketTimeoutException) {
                    log(Html.fromHtml("--------------> onFailure()<br>" + error.toString()).toString());
                    params.setRequestStatusLevel(RequestStatusLevel.TIMEOUT_ERROR);
                    sendHandler(params, callBackMethod);
                } else {
                    log(Html.fromHtml("--------------> onFailure()<br>" + error.toString()).toString());
                    params.setRequestStatusLevel(RequestStatusLevel.FAILURE);
                    sendHandler(params, callBackMethod);
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
     * @param requestBean
     * @param callBack
     */
    protected static void sendHandler(TaskBean requestBean, IHttpTaskCallBack callBack) {
        callBack.taskHandler(requestBean);
    }

    /**
     * 网络错误提示
     *
     * @param callBackMethod
     * @param params
     */
    protected static boolean networkError(IHttpTaskCallBack callBackMethod, TaskBean params) {
        if (!PhoneUtils.isNetworkOk(GlobalApplication.appContext)) {
            log("--------------> " + ResUtils.getStr(R.string.common_prompt_network));
            params.setRequestStatusLevel(RequestStatusLevel.NETWORK_ERROR);
            sendHandler(params, callBackMethod);
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
