package com.hebada.web.response;

import com.hebada.entity.ArticleStatus;
import org.springframework.util.StringUtils;

;

/**
 * Created by paddy on 2017/9/14.
 */
public class ArticleResponse {

    private static final String DEFAULT_ARTICLE_PAGE_IMAGE_URL = "";
    private long id;
    private String title;
    private String keyWords;
    private String articlePageImageUrl;
    private String content;
    private String author;
    private ArticleStatus status;
    private String createTime;
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

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getArticlePageImageUrl() {
        if (StringUtils.isEmpty(this.articlePageImageUrl)) return DEFAULT_ARTICLE_PAGE_IMAGE_URL;
        return articlePageImageUrl;
    }

    public void setArticlePageImageUrl(String articlePageImageUrl) {
        this.articlePageImageUrl = articlePageImageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
