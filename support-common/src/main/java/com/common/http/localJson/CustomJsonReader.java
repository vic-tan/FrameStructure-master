package com.common.http.localJson;

import android.content.Context;
import android.text.Html;

import com.common.bean.base.BaseJson;
import com.common.engine.interf.IHttpTaskCallBack;
import com.common.http.base.BaseHttpTask;
import com.common.http.base.RequestBean;
import com.common.utils.JsonUtils;
import com.common.utils.Logger;
import com.common.utils.StringUtils;
import com.constants.fixed.JsonConstants;
import com.constants.level.TaskRequestLevel;
import com.example.localinterface.JsonReader;


/**
 * 请求接口任务过程
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #readJson(RequestBean, IHttpTaskCallBack)}   读取本地自定义Json测试数据</li>
 * <li>{@link #readJson(Context, String)} 读取本地自定义Json测试数据</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class CustomJsonReader extends BaseHttpTask {


    /**
     * 读取本地自定义Json测试数据
     *
     * @param params
     * @param callBackMethod
     */
    public static void readJson(final RequestBean params, final IHttpTaskCallBack callBackMethod) {
        BaseJson jsonBean = null;
        try {
            String responseBody = JsonReader.getInstance().getJsonReaderFileContent(params.getContext(), params.getUrl());
            jsonBean = JsonUtils.parseToObjectBean(replaceId(responseBody), BaseJson.class);
            if (StringUtils.isEquals(jsonBean.getCode(), JsonConstants.CODE_SUCCEE)) {// 请求成功
                log("" + replaceId(responseBody));
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
                params.setRequestLevel(TaskRequestLevel.SERVICE_ERROR);
                sendHandler(params, callBackMethod);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log(Html.fromHtml("--------------> Exception (onSuccess)<br>" + e.toString()).toString());
            params.setRequestLevel(TaskRequestLevel.SERVICE_ERROR);
            sendHandler(params, callBackMethod);
        }
    }

    /**
     * junit 测试专用读取本地自定义Json测试数据
     *
     * @param url
     */
    public  String readJson(Context context, final String url) {
       /* BaseJson jsonBean = new BaseJson();
        try {
            String responseBody = getLocalCustomJson(context, url);
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
        }*/
        return "";
    }




}
