package com.android.tanlifei.framestructure.common.http.base;

import com.android.tanlifei.framestructure.common.constants.JsonConstants;
import com.android.tanlifei.framestructure.common.utils.MapUtils;

import java.util.Map;


/**
 * 请求接口参数基类，所有请求接口都继承此类
 * <ul>
 * <strong>基本方法及自己方法</strong>
 * <li>{@link #baseParams()}  共同基本参数</li>
 * <li>{@link #pageParams(int)} 分页请求参数</li>
 * </ul>
 *
 * @author tanlifei
 * @date 2015年2月14日 上午11:30:51
 */
public class BaseRequestParams {

    /**
     * 共同基本参数
     *
     * @return Map 返回类型
     * @Title: baseParams
     * @Description: 用一句话描述该文件做什么
     * @throws:throws
     */
    public static Map<String, Object> baseParams() {
        Map<String, Object> map = MapUtils.crateMap();
        return map;
    }

    /**
     * 分页请求参数
     *
     * @return  返回类型
     * @Title: pageParams
     * @Description: 分页请求参数(这些参数是固定的)
     * @throws:throws
     */
    public static Map<String, Object> pageParams(int pageNumber) {
        Map<String, Object> params = baseParams();
        params.put(JsonConstants.REQUEST_TASK_LIST_PARAM_PAGE_NUMBER, pageNumber);
        params.put(JsonConstants.REQUEST_TASK_LIST_PARAM_PAGE_SIZE, JsonConstants.PAGE_SIZE);
        return params;
    }

}
