package com.hebada.web.response;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by paddy on 2017/9/14.
 */
public class CatalogResponse {

    private long id;
    private String chineseName;
    private String englishName;
    private String templateName;
    private long parentId;
    private final List<CatalogResponse> children = Lists.newArrayList();

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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<CatalogResponse> getChildren() {
        return children;
    }
}
