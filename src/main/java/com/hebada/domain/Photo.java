package com.hebada.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by paddy.luo on 2017/9/19.
 */
@Entity(name = "t_photo")
public class Photo extends AbstractDomain {

    @Id
    @GeneratedValue
    protected long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "big_image_url")
    protected String bigImageUrl;

    @Column(name = "small_image_url")
    protected String smallImageUrl;

    @Column(name = "description")
    protected String description;

    @Column(name = "catalogId")
    protected long catalogId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBigImageUrl() {
        return bigImageUrl;
    }

    public void setBigImageUrl(String bigImageUrl) {
        this.bigImageUrl = bigImageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }
}
