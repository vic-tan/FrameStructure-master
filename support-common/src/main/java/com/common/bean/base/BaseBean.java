package com.common.bean.base;

/**
 * 所有实体bean 的基类
 *
 * @author tanlifei
 * @date 2015年4月1日 下午3:29:05
 */
public class BaseBean<T> {

    //	{"msg":"用户名或密码错误","data":null,"code":402}
    public String msg;
    public T data;
    public String code;

    public BaseBean() {
        super();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
