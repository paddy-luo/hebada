package com.hebada.web.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by paddy on 2017/9/16.
 */
public class PageResponse<T> {

    private int pageSize;
    private int totalPage;
    private int currentPage;
    private final List<T> content = Lists.newArrayList();

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getContent() {
        return content;
    }
}
