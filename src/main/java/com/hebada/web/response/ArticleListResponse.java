package com.hebada.web.response;

/**
 * Created by paddy.luo on 2017/9/19.
 */
public class ArticleListResponse {

    private long id;
    private String title;
    private String articlePageImageUrl;
    private String author;
    private String description;
    private String publishTime;

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

    public String getArticlePageImageUrl() {
        return articlePageImageUrl;
    }

    public void setArticlePageImageUrl(String articlePageImageUrl) {
        this.articlePageImageUrl = articlePageImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
