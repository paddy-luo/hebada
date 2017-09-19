package com.hebada.entity;

/**
 * Created by paddy.luo on 2017/9/4.
 */
public class URLs {

    public static final String DEFAULT = "/";
    public static final String LIST = "/list";
    public static final String ID = "/{id}";

    // user url
    public static final String USER = "/user";
    public static final String USER_LOGIN = "/login";
    public static final String USER_LOGOUT = "/logout";
    public static final String USER_ID = ID;
    public static final String USER_RETURN_LOGIN = URLs.USER + URLs.USER_LOGIN;

    // catalog url
    public static final String CATALOG = "/catalog";
    public static final String ARTICLE = "/article";
    public static final String PRODUCT = "/product";
    public static final String PHOTO = "/photo";

    public static final String CATALOG_ID = ID;
    public static final String CATALOG_LIST = LIST;
    public static final String CATALOG_CHILDREN_LIST = "/parent" + ID;
    public static final String CATALOG_LATEST_ARTICLE = "{catalogId}" + ARTICLE;
    public static final String CATALOG_ARTICLE_LIST = "{catalogId}" + ARTICLE + LIST;
    public static final String CATALOG_PRODUCT_LIST = "{catalogId}" + PRODUCT + LIST;
    public static final String CATALOG_PHOTO_LIST = "{catalogId}" + PHOTO + LIST;

    // article url include news ex.
    public static final String ARTICLE_ID = ID;
    public static final String ARTICLE_LIST = LIST;

    // product  url
    public static final String PRODUCT_ID = ID;

    //photo url
    public static final String PHOTO_UPLOAD = "/upload";
    public static final String PHOTO_ID = ID;
}
