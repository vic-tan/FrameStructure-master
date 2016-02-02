package com.common.bean.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.common.utils.StringUtils;

/**
 * 所有实体bean 的基类
 * @author tanlifei
 * @date 2015年4月1日 下午3:29:05
 */
public class BaseBean implements Parcelable {


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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected BaseBean(Parcel in) {
    }

    public static final Parcelable.Creator<BaseBean> CREATOR = new Parcelable.Creator<BaseBean>() {
        public BaseBean createFromParcel(Parcel source) {
            return new BaseBean(source);
        }

        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };
}
