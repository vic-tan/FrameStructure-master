package com.android.tanlifei.framestructure.common.http;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatus;
import com.android.tanlifei.framestructure.common.http.base.BaseRequestTask;
import com.android.tanlifei.framestructure.common.http.testData.JsonController;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class ReadLocalCustomJson extends BaseRequestTask {


    private static final String TAG = "RequestTask task";//日志过滤标识

    /**
     * 读取本地自定义Json测试数据
     *
     * @param url
     * @param handler
     */
    public static void readJson(final String url, final Handler handler) {
        try {
            String responseBody = JsonController.getLocalJson(url);
            JSONObject json = JSON.parseObject(responseBody.replace("\"id\":",
                    "\"my_id\":"));
            if (JsonUtils.isRequestSuccess(json)) {// 请求成功
                sendHandler(handler, RequestStatus.SUCCESS.value(), JsonUtils.getData(json), "onSuccess");
            } else {// 服务错误
                sendHandler(handler, RequestStatus.SERVICE_ERROR.value(), JsonUtils.parseToObjectBean(new String(responseBody),
                        BaseJson.class), "onSuccess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
