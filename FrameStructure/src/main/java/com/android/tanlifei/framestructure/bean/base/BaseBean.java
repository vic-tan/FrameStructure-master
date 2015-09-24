package com.android.tanlifei.framestructure.bean.base;

import com.android.tanlifei.framestructure.common.utils.StringUtils;

import java.io.Serializable;

/**
 * 所有实体bean 的基类
 * @author tanlifei
 * @date 2015年4月1日 下午3:29:05
 */
public class BaseBean implements Serializable {


    public BaseBean() {
        super();
    }

    /**
     * 显示 ""
     */
    protected String isEmpty(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

}
