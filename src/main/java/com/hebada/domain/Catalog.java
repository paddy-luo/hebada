package com.hebada.domain;

import com.google.common.collect.Lists;
import com.hebada.entity.RouterTemplateName;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by paddy on 2017/9/3.
 */
@Entity(name = "t_catalog")
public class Catalog extends CreateOrUpdateDomain {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "chinese_name")
    private String chineseName;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "parent_id")
    private long parentId;

    @Column(name = "t_template_name")
    @Enumerated(EnumType.STRING)
    private RouterTemplateName templateName;

    @Transient
    private final List<Catalog> children = Lists.newArrayList();

    public Catalog() {
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public RouterTemplateName getTemplateName() {
        return templateName;
    }

    public void setTemplateName(RouterTemplateName templateName) {
        this.templateName = templateName;
    }

    public List<Catalog> getChildren() {
        return children;
    }

}
