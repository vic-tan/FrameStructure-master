package com.android.tanlifei.framestructure.common.constants;

import com.android.tanlifei.framestructure.common.constants.enumConstants.OnOffLevel;

/**
 * 所有跟状态相关的常量，包括请求、异常、回调、返回状态...
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */

public class StatusConstants {

    /**
     * 是否开启异常状态
     */
    public static final OnOffLevel UNCAUGHT_EX_LEVEL = OnOffLevel.NONE;// 全局未捕获异常是否打开

    /**
     * 是否开启日志状态
     */
    public static final OnOffLevel LOG_LEVEL = OnOffLevel.FULL;// log日志是否打开

    /**
     * 是否开启请求接口成功读取对应的本的的自定义JSON
     */
    public static final OnOffLevel JSON_LEVEL = OnOffLevel.FULL;// 是否开启请求接口成功读取对应的本的的自定义JSON


}
