package com.android.tanlifei.framestructure.bean.base;

import com.android.tanlifei.framestructure.common.utils.StringUtils;

/**
 * json 最外层数据基本结构实体
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class BaseJson {

    private String code;
    private String msg;
    private String data;

    public BaseJson(String code, String msg, String data) {

        this.code = code;
        if(StringUtils.isEmpty(data) || StringUtils.isEquals(data,"null")){
            this.data = "";
        }else {
            this.data = data;
        }
        this.msg = msg;
    }

    public BaseJson(String data) {
        this.data = data;
    }

    public BaseJson() {
        super();
    }

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
        return "BaseJson{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
