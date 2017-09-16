package com.hebada.web.controller;

import com.hebada.converter.ArticleConverter;
import com.hebada.converter.CatalogConverter;
import com.hebada.domain.Article;
import com.hebada.domain.Catalog;
import com.hebada.entity.ArticleStatus;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ArticleService;
import com.hebada.service.CatalogService;
import com.hebada.web.request.CatalogRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@RestController
@RequestMapping(value = URLs.CATALOG)
@Api(value = "catalog api", basePath = URLs.CATALOG, description = "catalog api")
public class CatalogRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogRestController.class);

    @Inject
    private CatalogService catalogService;
    @Inject
    private ArticleService articleService;
    @Inject
    private CatalogConverter catalogConverter;
    @Inject
    private ArticleConverter articleConverter;

    // load all catalog
    @RequestMapping(value = URLs.CATALOG_LIST, method = RequestMethod.GET)
    @ApiOperation(value = "getCatalogList", httpMethod = HttpMethod.GET, notes = "catalog list")
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
    public AjaxResponse get(@PathVariable long id) {
        Catalog catalog = catalogService.get(id);
        return AjaxResponse.ok().withData(catalogConverter.convertToCatalogResponse(catalog));
    }

    @RequestMapping(value = URLs.CATALOG_ID, method = RequestMethod.PUT)
    @ApiOperation(value = "update", httpMethod = HttpMethod.PUT, notes = "update catalog")
    public AjaxResponse update(@PathVariable long id, @RequestBody CatalogRequest request) {
        request.setId(id);
        catalogService.update(catalogConverter.convertToCatalog(request));
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
    public AjaxResponse getChildrenList(@PathVariable(name = "id") long parentId) {
        List<Catalog> catalogList = catalogConverter.convertToCatalogList(catalogService.findAllByParentId(parentId));
        return AjaxResponse.ok().withData(catalogConverter.convertToCatalogResponse(catalogList));
    }

    @RequestMapping(value = URLs.CATALOG_LATEST_ARTICLE, method = RequestMethod.GET)
    @ApiOperation(value = "getLatestArticle", httpMethod = HttpMethod.GET, notes = "getLatestArticle")
    public AjaxResponse getLatestArticle(@PathVariable long catalogId) {
        Article article = new Article(catalogId);
        article.setStatus(ArticleStatus.PUBLISHED);
        Page<Article> articles = articleService.findArticles(article, new PageRequest(0, 10));
        if (!articles.hasContent()) return AjaxResponse.ok();
        return AjaxResponse.ok().withData(articleConverter.convertToArticleResponse(articles.getContent().get(0)));
    }
}
