package com.android.tanlifei.framestructure.common.constants;

/**
 * 所有跟JSON 相关的常量，包括json字段，结果
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class JsonConstants {

    // 分页参数
    public static final String REQUEST_TASK_LIST_PARAM_PAGE_SIZE = "pageSize";
    public static final String REQUEST_TASK_LIST_PARAM_PAGE_NUMBER = "pageNumber";
    public static final int PAGE_SIZE = 10; // 每页显示 的数据条数

    // JSON
    public static final String JSON_CODE = "code";
    public static final String JSON_MSG = "msg";
    public static final String JSON_DATA = "data";
    public static final String JSON_LIST = "list";

    public static final String JSON_RESULT = "result";//请求接口返回结果
    public static final String JSON_RESULT_SUCCEE = "1";//请求接口返回结果为成功
    public static final String JSON_RESULT_FAILURE = "0";//请求接口返 回结果为失败


    // 项目级别消息 用户验证问题
    public static final String CODE_SUCCEE = "0000";// 操作成功
    public static final String CODE_VALUE_0001 = "0001";// 查询用户异常
    public static final String CODE_VALUE_0002 = "0002";// 用户不存在
    public static final String CODE_VALUE_0003 = "0003";// 密码错误


}
