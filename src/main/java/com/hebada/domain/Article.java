package com.hebada.domain;

import com.hebada.entity.ArticleStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by paddy on 2017/9/8.
 */
@Entity(name = "t_article")
public class Article extends AbstractDomain {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "catalog_id")
    private long catalogId;

    @Column(name = "key_words")
    private String keyWords;

    @Column(name = "author")
    private String author;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ArticleStatus status;

    @Column(name = "publish_timed")
    private Date publishTimed;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public Date getPublishTimed() {
        return publishTimed;
    }

    public void setPublishTimed(Date publishTimed) {
        this.publishTimed = publishTimed;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
