package com.hebada.web.response;

/**
 * Created by paddy.luo on 2017/9/19.
 */
public class ArticleListResponse {

    private long id;
    private String title;
    private String articlePageImageUrl;
    private String description;

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
}
