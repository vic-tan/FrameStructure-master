package com.android.tanlifei.framestructure.common.http;

import android.text.Html;

import com.android.tanlifei.framestructure.R;
import com.android.tanlifei.framestructure.bean.base.BaseJson;
import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.constants.enumConstants.RequestStatusLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.TaskLevel;
import com.android.tanlifei.framestructure.common.http.base.BaseHttpTask;
import com.android.tanlifei.framestructure.common.http.base.CallbackParamBean;
import com.android.tanlifei.framestructure.common.http.base.RequestParamBean;
import com.android.tanlifei.framestructure.common.http.localJson.JsonController;
import com.android.tanlifei.framestructure.common.utils.JsonUtils;
import com.android.tanlifei.framestructure.common.utils.ResUtils;
import com.android.tanlifei.framestructure.common.utils.StringUtils;
import com.android.tanlifei.framestructure.engine.interf.IHttpTaskCallBack;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #readJson(RequestParamBean, IHttpTaskCallBack, TaskLevel)}   读取本地自定义Json测试数据</li>
 * <li>{@link #readJson(String)} 读取本地自定义Json测试数据</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class ReadLocalCustomJson extends BaseHttpTask {


    /**
     * 读取本地自定义Json测试数据
     *
     * @param params
     * @param callBackMethod
     * @param level
     */
    public static void readJson(final RequestParamBean params, final IHttpTaskCallBack callBackMethod, final TaskLevel level) {
        BaseJson jsonBean = null;
        try {
            String responseBody = JsonController.getLocalJson(params.getUrl());
            jsonBean = JsonUtils.parseToObjectBean(replaceId(responseBody), BaseJson.class);
            if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_SUCCEE)) {// 请求成功
                log("" + replaceId(responseBody));
                sendHandler(new CallbackParamBean(jsonBean, RequestStatusLevel.SUCCESS, level, params.getCallbackParams()), callBackMethod );
            } else {// 服务错误
                log("--------------> service error (onSuccess)");
                sendHandler(new CallbackParamBean(new BaseJson(), RequestStatusLevel.SERVICE_ERROR, level, params.getCallbackParams()), callBackMethod);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log(Html.fromHtml("--------------> Exception (onSuccess)<br>" + e.toString()).toString());
            sendHandler(new CallbackParamBean(new BaseJson(), RequestStatusLevel.SERVICE_ERROR, level, params.getCallbackParams()), callBackMethod);
            ;
        }
    }

    /**
     * junit 测试专用读取本地自定义Json测试数据
     *
     * @param url
     */
    public static String readJson(final String url) {
        BaseJson jsonBean = new BaseJson();
        try {
            String responseBody = JsonController.getLocalJson(url);
            jsonBean = JsonUtils.parseToObjectBean(replaceId(responseBody), BaseJson.class);
            if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_SUCCEE)) {// 请求成功
                return replaceId(responseBody);
            } else {// 服务错误
                jsonBean.setMsg(ResUtils.getStr(R.string.common_prompt_serivce));
                return ResUtils.getStr(R.string.common_prompt_serivce);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResUtils.getStr(R.string.common_prompt_serivce);
        }
    }


}
