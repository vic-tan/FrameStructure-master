package com.android.tanlifei.framestructure.bean.base;

/**
 * json 数据根结构
 */
public class BaseJson {

    private String code;
    private String msg;
    private String data;


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "HandlerJsonMsg [code=" + code + ", msg=" + msg + ", data="
                + data + "]";
    }

}
