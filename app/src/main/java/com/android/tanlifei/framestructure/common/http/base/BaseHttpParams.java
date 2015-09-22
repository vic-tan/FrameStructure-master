package com.android.tanlifei.framestructure.common.http.base;

import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.utils.MapUtils;

import java.util.Map;


/**
 * 请求接口参数基类，所有请求接口都继承此类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #baseParams(String)}  共同基本参数</li>
 * <li>{@link #pageParams(String,int)} 分页请求参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class BaseHttpParams {


    /**
     * 共同基本参数
     * @param url
     * @return
     */
    public static Map<String, Object> baseParams(String url) {
        Map<String, Object> map = MapUtils.crateMap();
        map.put(JsonConstants.JSON_TASK_URL,url);
        return map;
    }


    /**
     * 分页请求参数
     * @param url 请求url
     * @param pageNumber 当前页数
     * @return
     */
    public static Map<String, Object> pageParams(String url,int pageNumber) {
        Map<String, Object> params = baseParams(url);
        params.put(JsonConstants.REQUEST_TASK_LIST_PARAM_PAGE_NUMBER, pageNumber);
        params.put(JsonConstants.REQUEST_TASK_LIST_PARAM_PAGE_SIZE, JsonConstants.PAGE_SIZE);
        return params;
    }

}
