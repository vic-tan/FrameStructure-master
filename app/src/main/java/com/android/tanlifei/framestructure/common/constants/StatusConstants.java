package com.android.tanlifei.framestructure.common.constants;

import com.android.tanlifei.framestructure.common.constants.enumConstants.JsonLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.LogLevel;
import com.android.tanlifei.framestructure.common.constants.enumConstants.UncaughtExLevel;

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
    public static final UncaughtExLevel UNCAUGHT_EX_LEVEL = UncaughtExLevel.NONE;// 全局未捕获异常是否打开

    /**
     * 是否开启日志状态
     */
    public static final LogLevel LOG_LEVEL = LogLevel.FULL;// log日志是否打开

    /**
     * 是否开启请求接口成功读取对应的本的的自定义JSON
     */
    public static final JsonLevel JSON_LEVEL = JsonLevel.FULL;// 是否开启请求接口成功读取对应的本的的自定义JSON



    /**
     * 接口回调返回分类标识
     */
    public static final int CALLBACK_TAG_ONE = 0x00001;
    public static final int CALLBACK_TAG_TWO = 0x00002;
    public static final int CALLBACK_TAG_THREE = 0x00003;
    public static final int CALLBACK_TAG_FOUR = 0x00004;
    public static final int CALLBACK_TAG_FIVE = 0x00005;
    public static final int CALLBACK_TAG_SIX = 0x00006;
    public static final int CALLBACK_TAG_SEVEN = 0x00007;
}
