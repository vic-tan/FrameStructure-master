package com.android.tanlifei.framestructure.common.http;

import android.os.Handler;
import android.text.Html;

import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatus;
import com.android.tanlifei.framestructure.common.http.base.BaseRequestTask;
import com.android.tanlifei.framestructure.common.http.testData.JsonController;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.Logger;
import com.android.tanlifei.framestructure.common.utils.StringUtils;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #readJson(String, Handler)}  读取本地自定义Json测试数据</li>
 * <li>{@link #readJson(String)} 读取本地自定义Json测试数据</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class ReadLocalCustomJson extends BaseRequestTask {


    /**
     * 读取本地自定义Json测试数据
     *
     * @param url
     * @param handler
     */
    public static void readJson(final String url, final Handler handler) {
        String responseBody = "";
        try {
            responseBody = JsonController.getLocalJson(url);
            BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(responseBody), BaseJson.class);
            if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_VALUE_0000)) {// 请求成功
                Logger.i(TAG, "" + replaceId(responseBody));
                sendHandler(handler, RequestStatus.SUCCESS.value(), jsonBean.getData(), JsonUtils.format(replaceId(responseBody)).toString());
            } else {// 服务错误
                sendHandler(handler, RequestStatus.SERVICE_ERROR.value(), jsonBean, "--------------> service error (onSuccess)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendHandler(handler, RequestStatus.SERVICE_ERROR.value(), replaceId(responseBody), Html.fromHtml("--------------> Exception (onSuccess)<br>" + e.toString()).toString());
        }
    }

    /**
     * junit 测试专用读取本地自定义Json测试数据
     *
     * @param url
     */
    public static String readJson(final String url) {
        try {
            String responseBody = JsonController.getLocalJson(url);
            BaseJson jsonBean = JsonUtils.parseToObjectBean(replaceId(responseBody), BaseJson.class);
            if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_VALUE_0000)) {// 请求成功
                return jsonBean.getData();
            } else {// 服务错误
                return "服务错误";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage() + "服务错误";
        }
    }


}
