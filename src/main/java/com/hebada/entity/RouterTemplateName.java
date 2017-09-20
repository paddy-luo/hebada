package com.hebada.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by paddy on 2017/9/13.
 */
public enum RouterTemplateName {


    AR("ar", "通用文章模板"),
    TIME_LINE("timeline", "时间轴模板"),
    HONOR("honor", "荣誉列表模板"),
    TEAM("team", "团队列表模板"),
    NEWS("news", "新闻模板"),

    //todo: 模板内容要修改
    INDEX("index", "首页"),
    ABOUT("about", "简介"),
    ARTICLE("article", "文章"),
    PRODUCT("product", "产品"),
    DOWNLOAD("download", "下载"),
    PHOTO("photo", "图片"),
    RECRUITMENT("recruitment", "招聘"),
    FEED_BOOK("feedBook", "留言");


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
