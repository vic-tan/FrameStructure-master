package com.android.tanlifei.framestructure.common.constants.enumConstants;

/**
 * 网络请求提示状态状态
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public enum PromptStatus {

    /**
     * 网络错误
     */
    NETWORK_ERROR(0x0001),
    /**
     * 正在加载
     */
    PROGRESS(0x0002),
    /**
     * 服务器错误
     */
    SERVICE_ERROR(0x0003),
    /**
     * 请求超时
     */
    TIMEOUT_ERROR(0x0004),

    /**
     * 暂无数据
     */
    EMPTY_DATA(0x0005);



    private int value = 0x0000;

    private PromptStatus(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static PromptStatus RequestStatus(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0x0001:
                return NETWORK_ERROR;
            case 0x0002:
                return PROGRESS;
            case 0x0003:
                return SERVICE_ERROR;
            case 0x0004:
                return TIMEOUT_ERROR;
            case 0x0005:
                return EMPTY_DATA;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
