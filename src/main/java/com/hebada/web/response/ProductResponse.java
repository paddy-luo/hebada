package com.hebada.web.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by paddy on 2017/9/16.
 */
public class ProductResponse {

    private long id;
    private String name;
    private String alcoholicStrength;
    private String standard;
    private String description;
    private String content;
    private boolean recommended;
    private long catalogId;
    private final List<PhotoResponse> productImageUrls = Lists.newArrayList();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<PhotoResponse> getProductImageUrls() {
        return productImageUrls;
    }
}

