package com.hebada.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by paddy on 2017/9/13.
 */
public enum RouterTemplateName {

    INDEX("index", "首页"),
    AR("ar", "通用文章模板"),
    TIME_LINE("timeline", "时间轴模板"),
    HONOR("honor", "荣誉列表模板"),
    TEAM("team", "团队列表模板"),
    NEWS("news", "新闻模板"),
    PRODUCT("product", "产品模板"),
    FEED_BOOK("feedbook", "招聘模板"),
    RECRUITMENT("recruitment", "留言模板");

    private String name;
    private String description;

    RouterTemplateName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAllTemplateType() {
        List<String> templateNames = Lists.newArrayList();
        for (RouterTemplateName templateName : values()) {
            templateNames.add(templateName.getName());
        }
        return templateNames;
    }
}
