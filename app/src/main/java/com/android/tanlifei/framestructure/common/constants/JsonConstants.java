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


    public static final String JSON_LIST = "list";
    public static final String JSON_BEAN = "jsonBean";//请求返回Json内容在存储map的中的key值
    public static final String JSON_REQUEST_STATUS_LEVEL = "request_status_level";//请求网络方法状态在存储map的中的key值
    public static final String JSON_TASK_LEVEL = "task_level";//请求接口多任务存储map的中的key值标识
    public static final String JSON_TASK_URL = "url";//请求接口url存储map的中的key值
    // 项目级别消息 用户验证问题
    public static final String CODE_SUCCEE = "0000";// 操作成功



}
