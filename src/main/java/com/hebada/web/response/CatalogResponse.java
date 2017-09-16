package com.hebada.web.response;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by paddy on 2017/9/14.
 */
public class CatalogResponse {

    private long id;
    private String chineseName;
    private String englishName;
    private String templateName;
    private final Map<Long, CatalogResponse> children = Maps.newHashMap();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<Long, CatalogResponse> getChildren() {
        return children;
    }
}