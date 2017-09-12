package com.hebada.web.controller;

import com.hebada.converter.ArticleConverter;
import com.hebada.domain.Article;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ArticleService;
import com.hebada.web.request.ArticleRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;

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

  @Inject
  private ArticleService articleService;
  @Inject
  private ArticleConverter articleConverter;

  @RequestMapping(value = URLs.ARTICLE_ID, method = RequestMethod.GET)
  @ApiOperation(value = "get", httpMethod = HttpMethod.GET, notes = "get article")
  public AjaxResponse get(@PathVariable long id) {
    Article article = articleService.findOne(id);
    return AjaxResponse.ok().withData(article);
  }

  @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
  @ApiOperation(value = "save", httpMethod = HttpMethod.POST, notes = "save article")
  public AjaxResponse save(@RequestBody ArticleRequest request) {
    boolean saveOrUpdate = articleService.save(articleConverter.convertToArticle(request));
    if (saveOrUpdate) return AjaxResponse.ok();
    else return AjaxResponse.error();
  }

  @RequestMapping(value = URLs.ARTICLE_ID, method = RequestMethod.GET)
  @ApiOperation(value = "update", httpMethod = HttpMethod.GET, notes = "update article")
  public AjaxResponse update(@PathVariable long id, @RequestBody ArticleRequest request) {
    request.setId(id);
    articleService.update(articleConverter.convertToArticle(request));
    return AjaxResponse.ok();
  }

  @RequestMapping(value = URLs.ARTICLE_ID, method = RequestMethod.DELETE)
  @ApiOperation(value = "delete", httpMethod = HttpMethod.DELETE, notes = "delete article")
  public AjaxResponse delete(@PathVariable long id) {
    articleService.delete(id);
    return AjaxResponse.ok();
  }


}
