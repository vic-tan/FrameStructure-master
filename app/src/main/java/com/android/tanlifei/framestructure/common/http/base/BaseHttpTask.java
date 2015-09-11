package com.android.tanlifei.framestructure.common.http.base;
import android.os.Handler;
import android.text.Html;

import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.StatusConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.JsonLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.HttpTaskStatus;
import com.android.tanlifei.framestructure.common.http.ReadLocalCustomJson;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.StringUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.net.SocketTimeoutException;


/**
 * 请求接口任务过程基类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #setAsyncHttpResponseHandler(String, Handler)} response响应回调</li>
 * <li>{@link #sendHandler(Handler, int, String)} 发送Handler</li>
 * <li>{@link #sendHandler(Handler, int, Object, String)}  发送Handle</li>
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
     * @param handler
     * @return
     */
    protected static AsyncHttpResponseHandler setAsyncHttpResponseHandler(final String url, final Handler handler) {
        return new AsyncHttpResponseHandler() {

            @Override
            public void onFinish() {
                super.onFinish();
                sendHandler(handler, HttpTaskStatus.FINISH.value(), "--------------> onFinish()");

            }

            @Override
            public void onCancel() {
                super.onCancel();
                sendHandler(handler, HttpTaskStatus.CANCEL.value(), "--------------> onCancel()");
            }

            @Override
            public void onStart() {
                super.onStart();
                sendHandler(handler, HttpTaskStatus.START.value(), "--------------> onStart()");
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                sendHandler(handler, HttpTaskStatus.PROGRESS.value(), "");
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                try {
                    if (StatusConstants.JSON_LEVEL == JsonLevel.FULL) {//开启请求接口成功读取对应的本的的自定义JSON
                        ReadLocalCustomJson.readJson(url, handler);
                        return;
                    }
                    BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(new String(responseBody)), BaseJson.class);
                    if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_VALUE_0000)) {// 请求成功
                        Logger.i(TAG, "" + replaceId(new String(responseBody)));
                        sendHandler(handler, HttpTaskStatus.SUCCESS.value(), jsonBean, JsonUtils.format(replaceId(new String(responseBody))));
                    }else {// 服务错误
                        sendHandler(handler, HttpTaskStatus.SERVICE_ERROR.value(),jsonBean, "--------------> service error (onSuccess)");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    sendHandler(handler, HttpTaskStatus.SERVICE_ERROR.value(), new BaseJson(), Html.fromHtml("--------------> Exception (onSuccess)<br>"+e.toString()).toString());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                if (error instanceof SocketTimeoutException) {
                    sendHandler(handler, HttpTaskStatus.TIMEOUT_ERROR.value(),Html.fromHtml("--------------> onFailure()<br>"+error.toString()).toString());
                } else {
                    sendHandler(handler, HttpTaskStatus.FAILURE.value(), Html.fromHtml("--------------> onFailure()<br>"+error.toString()).toString());
                }
            }
        };
    }


    /**
     * 发送 Handler
     *
     * @param handler
     * @param logStr  调用方法的日志
     * @param what    标识
     */
    protected static void sendHandler(Handler handler, int what, String logStr) {
        if (!StringUtils.isEmpty(logStr)) {
            Logger.i(TAG, "" + logStr);
        }
        handler.obtainMessage(what)
                .sendToTarget();


    }

    /**
     * 发送 Handler
     *
     * @param handler
     * @param obj     内容
     * @param logStr  调用方法的日志
     * @param what    标识
     */
    protected static void sendHandler(Handler handler, int what, Object obj, String logStr) {
        if (!StringUtils.isEmpty(logStr)) {
            Logger.i(TAG, "" + logStr);
        }
        handler.obtainMessage(what, obj)
                .sendToTarget();


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
