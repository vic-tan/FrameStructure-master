package com.constants.level;

/**
 * 网络请求状态
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public enum DownloadStatusLevel {
    /**
     * 下载正在等待
     */
    IDLE(1),
    /**
     * 正在请求
     */
    WAITING(2),
    /**
     * 下载正在连接
     */
    CONNECTING(3),
    /**
     * 正在下载
     */
    DOWNLOADING(4),
    /**
     * 下载暂停
     */
    PAUSE(5),
    /**
     * 下载重置
     */
    RESUME(6),
    /**
     * 下载取消
     */
    CANCEL(7),
    /**
     * 下载完成
     */
    DONE(8),
    /**
     * 下载错误
     */
    ERROR(9);

    private int value = 0;

    private DownloadStatusLevel(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static DownloadStatusLevel HttpTaskStatus(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 1:
                return IDLE;
            case 2:
                return WAITING;
            case 3:
                return CONNECTING;
            case 4:
                return DOWNLOADING;
            case 5:
                return PAUSE;
            case 6:
                return RESUME;
            case 7:
                return CANCEL;
            case 8:
                return DONE;
            case 9:
                return ERROR;
            default:
                return IDLE;
        }
    }

    public int value() {
        return this.value;
    }
}
