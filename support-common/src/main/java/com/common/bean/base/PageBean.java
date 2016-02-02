package com.common.bean.base;


import android.os.Parcel;
import android.os.Parcelable;

import com.constants.fixed.JsonConstants;


/**
 * 列表下拉上拉分页基本参数管理
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class PageBean extends BaseBean implements Parcelable {

    public static final Parcelable.Creator<PageBean> CREATOR = new Parcelable.Creator<PageBean>() {
        public PageBean createFromParcel(Parcel source) {
            return new PageBean(source);
        }

        public PageBean[] newArray(int size) {
            return new PageBean[size];
        }
    };
    private int pageSize;//页大小
    private int pageNumber;//当前页数
    private int totalRow;//总记录数
    private int totalPage;//总页数

    public PageBean() {
        super();
    }

    protected PageBean(Parcel in) {
        this.pageSize = in.readInt();
        this.pageNumber = in.readInt();
        this.totalRow = in.readInt();
        this.totalPage = in.readInt();
    }

    public int getPageSize() {
        if (pageSize == 0)
            return JsonConstants.PAGE_SIZE;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        if (pageNumber == 0)
            return 1;
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalRow=" + totalRow +
                ", totalPage=" + totalPage +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pageSize);
        dest.writeInt(this.pageNumber);
        dest.writeInt(this.totalRow);
        dest.writeInt(this.totalPage);
    }
}
