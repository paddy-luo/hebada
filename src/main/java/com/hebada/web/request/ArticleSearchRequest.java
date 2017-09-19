package com.hebada.web.request;

import com.hebada.entity.ArticleStatus;

/**
 * Created by paddy on 2017/9/16.
 */
public class ArticleSearchRequest {

    private String title;
    private long catalogId;
    private ArticleStatus status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }
}
