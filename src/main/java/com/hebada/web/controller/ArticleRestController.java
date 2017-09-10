package com.hebada.web.controller;

import com.hebada.convertor.ArticleConverter;
import com.hebada.domain.Article;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ArticleService;
import com.hebada.web.request.ArticleRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy on 2017/9/9.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLs.ARTICLE)
@Api(basePath = URLs.ARTICLE, description = "article api")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleConverter articleConverter;

    @RequestMapping(value = URLs.ARTICLE_ID, method = RequestMethod.GET)
    @ApiOperation(value = "findOne", httpMethod = HttpMethod.GET, notes = "findOne article")
    public AjaxResponse get(@PathVariable long id) {
        Article article = articleService.findOne(id);
        return AjaxResponse.ok().withData(article);
    }

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", httpMethod = HttpMethod.POST, notes = "save article")
    public AjaxResponse save(@RequestBody ArticleRequest request) {
        boolean saveOrUpdate = articleService.saveOrUpdate(articleConverter.convertToArticle(request));
        if (saveOrUpdate) return AjaxResponse.ok();
        else return AjaxResponse.error();
    }

    @RequestMapping(value = URLs.ARTICLE_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", httpMethod = HttpMethod.DELETE, notes = "delete article")
    public AjaxResponse delete(@PathVariable long id) {
        articleService.delete(id);
        return AjaxResponse.ok();
    }


}
