package com.common.http.base;


import android.text.Html;

import com.common.R;
import com.common.bean.base.BaseJson;
import com.common.engine.interf.IHttpTaskCallBack;
import com.common.http.localJson.CustomJsonReader;
import com.common.utils.JsonUtils;
import com.common.utils.Logger;
import com.common.utils.NetUtils;
import com.common.utils.ResUtils;
import com.common.utils.StringUtils;
import com.constants.fixed.JsonConstants;
import com.constants.level.TaskRequestLevel;
import com.example.localinterface.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.net.SocketTimeoutException;
import java.util.Properties;


/**
 * 请求接口任务过程基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #setAsyncHttpResponseHandler(RequestBean, IHttpTaskCallBack)}  response响应回调</li>
 * <li>{@link #sendHandler(RequestBean, IHttpTaskCallBack)}  发送回调</li>
 * <li>{@link #replaceId(String)} 把json 中的"id" key  替换成"my_id" key ,这样做是为了跟 litepal 或GreenDao 等关系型数据库库自带的id冲突</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class BaseHttpTask {

    protected static final String TAG = "HttpTask task";//日志过滤标识
    private static Properties urlProps;



    /**
     * response响应回调
     *
     * @param callBackMethod
     * @return
     */
    protected static AsyncHttpResponseHandler setAsyncHttpResponseHandler(final RequestBean params, final IHttpTaskCallBack callBackMethod) {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onFinish() {
                super.onFinish();
                log("--------------> onFinish()");
                params.setRequestLevel(TaskRequestLevel.FINISH);
                sendHandler(params, callBackMethod);
            }

            @Override
            public void onCancel() {
                super.onCancel();
                log("--------------> onCancel()");
                params.setRequestLevel(TaskRequestLevel.CANCEL);
                sendHandler(params, callBackMethod);
            }

            @Override
            public void onStart() {
                super.onStart();
                log("--------------> onStart()");
                params.setRequestLevel(TaskRequestLevel.START);
                sendHandler(params, callBackMethod);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                params.setRequestLevel(TaskRequestLevel.PROGRESS);
                sendHandler(params, callBackMethod);
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                try {
                    //开启请求接口成功读取对应的本的的自定义JSON,开启了单个Url本地Json调试
                    if (JsonReader.getInstance().getJsonReader(params.getUrl())) {
                        CustomJsonReader.readJson(params, callBackMethod);
                        return;
                    }
                    BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(responseBody)), BaseJson.class);
                    if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_SUCCEE)) {// 请求成功
                        log("" + replaceId(new String(responseBody)));
                        Logger.json(TAG, "" + replaceId(new String(responseBody)));
                        if (StringUtils.isEmpty(jsonBean.getData())) {
                            params.setRequestLevel(TaskRequestLevel.EMPTY_DATA);
                            params.setBaseJson(jsonBean);
                            sendHandler(params, callBackMethod);
                        } else {
                            params.setRequestLevel(TaskRequestLevel.SUCCESS);
                            params.setBaseJson(jsonBean);
                            sendHandler(params, callBackMethod);
                        }
                    } else {// 服务错误
                        log("--------------> service error (onSuccess)");
                        params.setRequestLevel(TaskRequestLevel.EXCEPTION);
                        sendHandler(params, callBackMethod);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log(Html.fromHtml("--------------> Exception (onSuccess)<br>" + e.toString()).toString());
                    params.setRequestLevel(TaskRequestLevel.EXCEPTION);
                    sendHandler(params, callBackMethod);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (error instanceof SocketTimeoutException) {
                    log(Html.fromHtml("--------------> onFailure()<br>" + error.toString()).toString());
                    params.setRequestLevel(TaskRequestLevel.TIMEOUT_ERROR);
                    sendHandler(params, callBackMethod);
                } else {
                    log(Html.fromHtml("--------------> onFailure()<br>" + error.toString()).toString());
                    params.setRequestLevel(TaskRequestLevel.FAILURE);
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
    protected static void sendHandler(RequestBean requestBean, IHttpTaskCallBack callBack) {
        callBack.taskCallBack(requestBean);
    }

    /**
     * 网络错误提示
     *
     * @param callBackMethod
     * @param params
     */
    protected static boolean networkError(IHttpTaskCallBack callBackMethod, RequestBean params) {
        if (!NetUtils.isConnected(params.getContext())) {
            log("--------------> " + ResUtils.getStr(R.string.common_prompt_network));
            params.setRequestLevel(TaskRequestLevel.NETWORK_ERROR);
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
