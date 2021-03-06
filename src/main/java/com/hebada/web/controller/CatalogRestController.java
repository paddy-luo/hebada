package com.hebada.web.controller;

import com.hebada.converter.ArticleConverter;
import com.hebada.converter.CatalogConverter;
import com.hebada.converter.PhotoConverter;
import com.hebada.converter.ProductConverter;
import com.hebada.domain.Article;
import com.hebada.domain.Catalog;
import com.hebada.domain.Photo;
import com.hebada.domain.Product;
import com.hebada.entity.ArticleStatus;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ArticleService;
import com.hebada.service.CatalogService;
import com.hebada.service.PhotoService;
import com.hebada.service.ProductService;
import com.hebada.web.interceptor.LoginRequired;
import com.hebada.web.request.CatalogRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@RestController
@RequestMapping(value = URLs.CATALOG)
@Api(value = "catalog api", basePath = URLs.CATALOG, description = "catalog api")
@LoginRequired
public class CatalogRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogRestController.class);

    @Inject
    private CatalogService catalogService;
    @Inject
    private ArticleService articleService;
    @Inject
    private ProductService productService;
    @Inject
    private CatalogConverter catalogConverter;
    @Inject
    private ArticleConverter articleConverter;
    @Inject
    private ProductConverter productConverter;
    @Inject
    private PhotoService photoService;
    @Inject
    private PhotoConverter photoConverter;

    // load all catalog
    @RequestMapping(value = URLs.CATALOG_LIST, method = RequestMethod.GET)
    @ApiOperation(value = "getCatalogList", httpMethod = HttpMethod.GET, notes = "catalog list")
    @LoginRequired(required = false)
    public AjaxResponse getCatalogList() {
        List<Catalog> catalogList = catalogConverter.convertToCatalogList(catalogService.findAll());
        return AjaxResponse.ok().withData(catalogConverter.convertToCatalogResponse(catalogList));
    }

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", httpMethod = HttpMethod.POST, notes = "save catalog")
    public AjaxResponse save(@RequestBody CatalogRequest request) {
        LOGGER.info("request params: {}", request);
        boolean saveOrUpdate = catalogService.save(catalogConverter.convertToCatalog(request));
        if (saveOrUpdate) return AjaxResponse.ok();
        else return AjaxResponse.error();
    }

    @RequestMapping(value = URLs.CATALOG_ID, method = RequestMethod.GET)
    @ApiOperation(value = "get", httpMethod = HttpMethod.GET, notes = "findOne catalog")
    @LoginRequired(required = false)
    public AjaxResponse get(@PathVariable long id) {
        Catalog catalog = catalogService.get(id);
        return AjaxResponse.ok().withData(catalogConverter.convertToCatalogResponse(catalog));
    }

    @RequestMapping(value = URLs.CATALOG_ID, method = RequestMethod.PUT)
    @ApiOperation(value = "update", httpMethod = HttpMethod.PUT, notes = "update catalog")
    public AjaxResponse update(@PathVariable long id, @RequestBody CatalogRequest request) {
        catalogService.update(id, catalogConverter.convertToCatalog(request));
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.CATALOG_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", httpMethod = HttpMethod.DELETE, notes = "delete catalog")
    public AjaxResponse delete(@PathVariable long id) {
        catalogService.delete(id);
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.CATALOG_CHILDREN_LIST, method = RequestMethod.GET)
    @ApiOperation(value = "getChildrenList", httpMethod = HttpMethod.GET, notes = "get catalog childrenList")
    @LoginRequired(required = false)
    public AjaxResponse getChildrenList(@PathVariable(name = "id") long parentId) {
        List<Catalog> catalogList = catalogConverter.convertToCatalogList(catalogService.findAllByParentId(parentId));
        return AjaxResponse.ok().withData(catalogConverter.convertToCatalogResponse(catalogList));
    }

    @RequestMapping(value = URLs.CATALOG_LATEST_ARTICLE, method = RequestMethod.GET)
    @ApiOperation(value = "getLatestArticle", httpMethod = HttpMethod.GET, notes = "getLatestArticle")
    @LoginRequired(required = false)
    public AjaxResponse getLatestArticle(@PathVariable long catalogId) {
        Article article = new Article(catalogId);
        article.setStatus(ArticleStatus.PUBLISHED);
        Page<Article> articles = articleService.findArticles(article, new PageRequest(0, 10));
        if (!articles.hasContent()) return AjaxResponse.ok();
        return AjaxResponse.ok().withData(articleConverter.convertToArticleResponse(articles.getContent().get(0)));
    }

    // 产品模块的列表
    //todo: 产品表和产品图片表分开， 这边要做调整
    @RequestMapping(value = URLs.CATALOG_PRODUCT_LIST, method = RequestMethod.POST)
    @ApiOperation(value = "list", notes = "list", httpMethod = HttpMethod.POST)
    @LoginRequired(required = false)
    public AjaxResponse getProductList(@PathVariable long catalogId, @RequestBody com.hebada.web.request.PageRequest request) {
        Page<Product> productPage = productService.findAllByCatalogId(catalogId, request.getPageNumber(), request.getPageSize());
        List<Photo> photoList = photoService.findPhotoListByProductIds(productConverter.convertToProductIds(productPage.getContent()));
        Map<Long, Photo> photoMap = photoConverter.convertToPhotoMap(photoList);
        return AjaxResponse.ok().withData(productConverter.convertToProductListResponse(productPage
            , request.getPageNumber(), request.getPageSize(), photoMap));
    }

    // 获取资质荣誉等 图片列表
    @RequestMapping(value = URLs.CATALOG_PHOTO_LIST, method = RequestMethod.POST)
    @ApiOperation(value = "photo list", notes = "photo list", httpMethod = HttpMethod.POST)
    @LoginRequired(required = false)
    public AjaxResponse getPhotoList(@PathVariable long catalogId, @RequestBody com.hebada.web.request.PageRequest request) {
        Page<Photo> photoPage = photoService.findPhotoListByCatalogId(catalogId, request.getPageNumber(), request.getPageSize());
        return AjaxResponse.ok().withData(photoConverter.convertToPagePhotoResponse(photoPage,
            request.getPageNumber(), request.getPageSize()));
    }
}
