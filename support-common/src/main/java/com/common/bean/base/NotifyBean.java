package com.common.bean.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanlifei on 16/3/8.
 */
public class NotifyBean implements Parcelable {
    private int iconId;
    private String title;
    private String content;
    private long time;
    private int notifyId;

    public int getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(int notifyId) {
        this.notifyId = notifyId;
    }

    public String getContent() {
        return content;
    }

    public static Creator<NotifyBean> getCREATOR() {
        return CREATOR;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotifyBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.iconId);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeLong(this.time);
        dest.writeInt(this.notifyId);
    }

    protected NotifyBean(Parcel in) {
        this.iconId = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.time = in.readLong();
        this.notifyId = in.readInt();
    }

    public static final Creator<NotifyBean> CREATOR = new Creator<NotifyBean>() {
        public NotifyBean createFromParcel(Parcel source) {
            return new NotifyBean(source);
        }

        public NotifyBean[] newArray(int size) {
            return new NotifyBean[size];
        }
    };
}
