package com.hebada.web.response;

/**
 * Created by paddy on 2017/9/21.
 */
public class ProductListResponse {

    private long id;
    private String name;
    private String alcoholicStrength;
    private String standard;
    private String productImageUrl;
    private String description;

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

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
