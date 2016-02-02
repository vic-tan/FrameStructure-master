package com.constants.fixed;


import com.constants.level.OnOffLevel;

/**
 * 所有跟状态开关常量控制.....
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */

public class OnOffConstants {

    /**
     * 是否开启异常状态
     */
    public static final OnOffLevel UNCAUGHT_EX_LEVEL = OnOffLevel.OFF;// 全局未捕获异常是否打开

    /**
     * 是否开启日志状态
     */
    public static final OnOffLevel LOG_LEVEL = OnOffLevel.ON;// log日志是否打开


}
