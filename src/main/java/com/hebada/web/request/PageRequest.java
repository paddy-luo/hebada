package com.hebada.web.request;

/**
 * Created by paddy on 2017/9/3.
 */
public class PageRequest<T> {

    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 10;

    private int pageNumber;
    private int pageSize;

    private T params;

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

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
