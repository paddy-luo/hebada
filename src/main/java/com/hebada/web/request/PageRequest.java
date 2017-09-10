package com.hebada.web.request;

/**
 * Created by paddy on 2017/9/3.
 */
public class PageRequest {

    protected static final int DEFAULT_PAGE_NUM = 1;
    protected static final int DEFAULT_PAGE_SIZE = 10;

    protected int pageNumber;
    protected int pageSize;

    public int getPageNumber() {
        if (this.pageNumber <= 0) return DEFAULT_PAGE_NUM;
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        if (this.pageSize <= 0) return DEFAULT_PAGE_SIZE;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
