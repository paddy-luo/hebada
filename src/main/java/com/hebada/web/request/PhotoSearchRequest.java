package com.hebada.web.request;

/**
 * Created by paddy.luo on 2017/9/29.
 */
public class PhotoSearchRequest {

    private String name;
    private String catalogName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }
}
