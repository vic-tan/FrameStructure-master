package com.common.bean.paramsBean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanlifei on 16/1/12.
 */
public class PhotoBean implements Parcelable {

    private final String thumbnail;//缩略图
    private final String artwork;//原始图

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private final int defaultId;//未加载时默认显示图片
    private int width;//原始位置图宽
    private int height;//原始位置图宽
    private int locationX;//原始X轴位置
    private int locationY;//原始Y轴位置

    public PhotoBean(String thumbnail, String artwork, int defaultId) {
        this.thumbnail = thumbnail;
        this.artwork = artwork;
        this.defaultId = defaultId;
    }

    public String getArtwork() {
        return artwork;
    }

    public int getDefaultId() {
        return defaultId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thumbnail);
        dest.writeString(this.artwork);
        dest.writeInt(this.defaultId);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.locationX);
        dest.writeInt(this.locationY);
    }

    protected PhotoBean(Parcel in) {
        this.thumbnail = in.readString();
        this.artwork = in.readString();
        this.defaultId = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        this.locationX = in.readInt();
        this.locationY = in.readInt();
    }

    public static final Creator<PhotoBean> CREATOR = new Creator<PhotoBean>() {
        public PhotoBean createFromParcel(Parcel source) {
            return new PhotoBean(source);
        }

        public PhotoBean[] newArray(int size) {
            return new PhotoBean[size];
        }
    };
}
