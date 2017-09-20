package com.hebada.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@Entity(name = "t_product")
public class Product extends CreateOrUpdateDomain {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "alcoholic_strength")
    private String alcoholicStrength;
    @Column(name = "standard")
    private String standard;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "recommended")
    private boolean recommended;
    @Column(name = "catalogId")
    private long catalogId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getAlcoholicStrength() {
        return alcoholicStrength;
    }

    public void setAlcoholicStrength(String alcoholicStrength) {
        this.alcoholicStrength = alcoholicStrength;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }
}
