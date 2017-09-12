package com.hebada.web.controller;

import com.hebada.converter.ProductConverter;
import com.hebada.domain.Product;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ProductService;
import com.hebada.web.request.CommonRequest;
import com.hebada.web.request.PageRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy on 2017/9/10.
 */
@RestController
@RequestMapping(value = URLs.PRODUCT)
@Api(basePath = URLs.PRODUCT, description = "product api")
public class ProductRestController {

  @Inject
  private ProductService productService;
  @Inject
  private ProductConverter productConverter;

  @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
  @ApiOperation(value = "save", notes = "save", httpMethod = HttpMethod.POST)
  public AjaxResponse save(@RequestBody CommonRequest request) {
    productService.save(productConverter.convertToProduct(request));
    return AjaxResponse.ok();
  }

  @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.GET)
  @ApiOperation(value = "get", notes = "get", httpMethod = HttpMethod.GET)
  public AjaxResponse get(@PathVariable long id) {
    productService.get(id);
    return AjaxResponse.ok();
  }

  @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.POST)
  @ApiOperation(value = "update", notes = "update", httpMethod = HttpMethod.POST)
  public AjaxResponse update(@PathVariable long id, @RequestBody CommonRequest request) {
    request.setId(id);
    productService.update(productConverter.convertToProduct(request));
    return AjaxResponse.ok();
  }

  @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.DELETE)
  @ApiOperation(value = "delete", notes = "delete", httpMethod = HttpMethod.DELETE)
  public AjaxResponse delete(@PathVariable long id) {
    productService.delete(id);
    return AjaxResponse.ok();
  }

  @RequestMapping(value = URLs.PRODUCT_LIST, method = RequestMethod.POST)
  @ApiOperation(value = "list", notes = "list", httpMethod = HttpMethod.POST)
  public AjaxResponse list(@RequestBody PageRequest request) {
    Page<Product> all = productService.findAll(new org.springframework.data.
        domain.PageRequest(request.getPageNumber(), request.getPageSize()));
    return AjaxResponse.ok().withData(all);
  }
}
