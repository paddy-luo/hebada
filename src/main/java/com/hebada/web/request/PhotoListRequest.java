package com.hebada.web.request;

import com.google.common.collect.Lists;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by paddy.luo on 2017/9/19.
 */
public class PhotoListRequest {

    private long id;
    @NotNull(message = "photo name is null")
    private String name;
    private long productId;
    @NotEmpty(message = "bigImageUrl is null")
    private final List<String> bigImageUrl = Lists.newArrayList();
    @NotEmpty(message = "smallImageUrl is null")
    private final List<String> smallImageUrl = Lists.newArrayList();
    private String description;
    private long catalogId;

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

    public List<String> getBigImageUrl() {
        return bigImageUrl;
    }

    public List<String> getSmallImageUrl() {
        return smallImageUrl;
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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
