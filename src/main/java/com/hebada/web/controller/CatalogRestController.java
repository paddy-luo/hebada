package com.hebada.web.controller;

import com.hebada.convertor.CatalogConverter;
import com.hebada.domain.Catalog;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.CatalogService;
import com.hebada.web.request.CatalogRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@RestController
@RequestMapping(value = URLs.CATALOG)
@Api(value = "catalog api", basePath = URLs.CATALOG, description = "catalog api")
public class CatalogRestController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private CatalogConverter catalogConverter;

    // 加载所有类目
    @RequestMapping(value = URLs.CATALOG_LIST, method = RequestMethod.GET)
    @ApiOperation(value = "getCatalogList", httpMethod = HttpMethod.GET, notes = "catalog list")
    public AjaxResponse getCatalogList() {
        List<Catalog> catalogList = catalogConverter.convertToCatalogList(catalogService.findAll());
        return AjaxResponse.ok().withData(catalogList);
    }

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", httpMethod = HttpMethod.POST, notes = "save catalog")
    public AjaxResponse save(@RequestBody CatalogRequest request) {
        boolean saveOrUpdate = catalogService.saveOrUpdate(catalogConverter.convertToCatalog(request));
        if (saveOrUpdate) return AjaxResponse.ok();
        else return AjaxResponse.error();
    }

    @RequestMapping(value = URLs.CATALOG_ID, method = RequestMethod.GET)
    @ApiOperation(value = "get", httpMethod = HttpMethod.GET, notes = "findOne catalog")
    public AjaxResponse get(@PathVariable long id) {
        Catalog catalog = catalogService.get(id);
        return AjaxResponse.ok().withData(catalog);
    }

    @RequestMapping(value = URLs.CATALOG_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", httpMethod = HttpMethod.DELETE, notes = "delete catalog")
    public AjaxResponse delete(@PathVariable long id) {
        catalogService.delete(id);
        return AjaxResponse.ok();
    }
}
