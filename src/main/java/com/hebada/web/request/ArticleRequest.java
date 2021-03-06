package com.hebada.web.request;

import com.hebada.entity.ArticleStatus;

/**
 * Created by paddy on 2017/9/9.
 */
public class ArticleRequest {

    private long id;
    private String title;
    private String author;
    private long catalogId;
    private String keyWords;
    private String articlePageImageUrl;
    private boolean recommended;
    private String content;
    private ArticleStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticlePageImageUrl() {
        return articlePageImageUrl;
    }

    public void setArticlePageImageUrl(String articlePageImageUrl) {
        this.articlePageImageUrl = articlePageImageUrl;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
