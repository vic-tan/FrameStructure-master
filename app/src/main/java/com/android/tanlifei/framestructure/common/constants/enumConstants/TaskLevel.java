package com.android.tanlifei.framestructure.common.constants.enumConstants;

/**
 * 网络接口区分任务状态
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public enum TaskLevel {
    /**
     * 每一个请求任务
     */
    TASK_ONE(0x10001),
    /**
     * 每二个请求任务
     */
    TASK_TWO(0x10002),
    /**
     * 每三个请求任务
     */
    TASK_THREE(0x10003),
    /**
     * 每四个请求任务
     */
    TASK_FOUR(0x10004),
    /**
     * 每五个请求任务
     */
    TASK_FIVE(0x10005),
    /**
     * 每六个请求任务
     */
    TASK_FIX(0x10006),
    /**
     * 每七个请求任务
     */
    TASK_SEVEN(0x10007),
    /**
     * 每八个请求任务
     */
    TASK_EIGHT(0x10008),
    /**
     * 每九个请求任务
     */
    TASK_NINE(0x10009),
    /**
     * 每十个请求任务
     */
    TASK_TEN(0x10010),
    /**
     * 不返回标识
     */
    TASK_EMPTY(-0x11111);

    private int value = 0x0000;

    private TaskLevel(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static TaskLevel TaskLevel(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0x1001:
                return TASK_ONE;
            case 0x1002:
                return TASK_TWO;
            case 0x1003:
                return TASK_THREE;
            case 0x1004:
                return TASK_FOUR;
            case 0x1005:
                return TASK_FIVE;
            case 0x1006:
                return TASK_FIX;
            case 0x1007:
                return TASK_SEVEN;
            case 0x1008:
                return TASK_EIGHT;
            case 0x1009:
                return TASK_NINE;
            case 0x1010:
                return TASK_TEN;
            case -0x11111:
                return TASK_EMPTY;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
