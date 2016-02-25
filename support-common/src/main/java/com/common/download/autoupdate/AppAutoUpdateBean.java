package com.common.download.autoupdate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanlifei on 16/2/24.
 */
public class AppAutoUpdateBean implements Parcelable {
    private String version_code;
    private String version_name;
    private String name;
    private String url;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version_code);
        dest.writeString(this.version_name);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.desc);
    }

    public AppAutoUpdateBean() {
    }

    protected AppAutoUpdateBean(Parcel in) {
        this.version_code = in.readString();
        this.version_name = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<AppAutoUpdateBean> CREATOR = new Parcelable.Creator<AppAutoUpdateBean>() {
        public AppAutoUpdateBean createFromParcel(Parcel source) {
            return new AppAutoUpdateBean(source);
        }

        public AppAutoUpdateBean[] newArray(int size) {
            return new AppAutoUpdateBean[size];
        }
    };
}
