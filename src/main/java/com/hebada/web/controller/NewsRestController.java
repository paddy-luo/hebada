package com.hebada.web.controller;

import com.hebada.domain.News;
import com.hebada.entity.URLs;
import com.hebada.service.NewsService;
import com.hebada.web.request.NewsPageRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy on 2017/9/3.
 */
@RestController
@RequestMapping(value = URLs.NEWS)
@Api(description = "News Operation", value = "NewsController api", basePath = URLs.NEWS)
public class NewsRestController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = URLs.NEWS_ID, method = RequestMethod.GET)
    @ApiOperation(value = "get", notes = "get news by id")
    public AjaxResponse get(@PathVariable long id) {
        News news = newsService.findById(id);
        if (news != null) return AjaxResponse.ok().withData(news);
        else return AjaxResponse.error();
    }

    @RequestMapping(value = URLs.NEWS_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", notes = "delete news by id")
    public AjaxResponse delete(@PathVariable long id) {
        newsService.deleteById(id);
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = URLs.DEFAULT, notes = "saveOrUpdate news", httpMethod = "POST")
    public AjaxResponse save(@RequestBody News news) {
        boolean saveOrUpdate = newsService.saveOrUpdate(news);
        if (saveOrUpdate) return AjaxResponse.ok();
        else return AjaxResponse.error().withDescription("操作失败");
    }

    @RequestMapping(value = URLs.NEWS_LIST, method = RequestMethod.GET)
    @ApiOperation(value = URLs.NEWS_LIST, notes = "news list", httpMethod = "GET")
    public Page<News> getPage(@RequestBody NewsPageRequest request) {
        return newsService.findByTitleOrNewsType(request.getTitle(), request.getNewsType()
            , new PageRequest(request.getPageNumber(), request.getPageSize()));
    }
}
