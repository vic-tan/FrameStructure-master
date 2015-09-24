package com.android.tanlifei.framestructure.bean.base;


import com.android.tanlifei.framestructure.common.constants.JsonConstants;

import java.io.Serializable;


/**
 * 列表下拉上拉分页基本参数管理
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class PageBean extends BaseBean implements Serializable {

    private int pageSize;//页大小
    private int pageNumber;//当前页数
    private int totalRow;//总记录数
    private int totalPage;//总页数

    public PageBean() {
        super();
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
}
