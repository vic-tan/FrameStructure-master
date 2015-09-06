package com.android.tanlifei.framestructure.common.constants.enumConstants;

/**
 * 网络请求状态
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public enum RequestStatus {
    /**
     * 开始请求
     */
    START(1),
    /**
     * 正在请求
     */
    PROGRESS(2),
    /**
     * 请求成功
     */
    SUCCESS(3),
    /**
     * 请求失败
     */
    FAILURE(4),
    /**
     * 请求完成
     */
    FINISH(5),
    /**
     * 取消请求
     */
    CANCEL(6),
    /**
     * 服务器错误
     */
    SERVICE_ERROR(7),
    /**
     * 请求超时
     */
    TIMEOUT_ERROR(8);

    private int value = 0;

    private RequestStatus(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static RequestStatus RequestStatus(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 1:
                return START;
            case 2:
                return PROGRESS;
            case 3:
                return SUCCESS;
            case 4:
                return FAILURE;
            case 5:
                return FINISH;
            case 6:
                return CANCEL;
            case 7:
                return SERVICE_ERROR;
            case 8:
                return TIMEOUT_ERROR;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
