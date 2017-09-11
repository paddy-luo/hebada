package com.hebada.web.controller;

import com.hebada.convertor.ProductConverter;
import com.hebada.domain.Product;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ProductService;
import com.hebada.web.request.CommonRequest;
import com.hebada.web.request.PageRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConverter productConverter;

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", notes = "save", httpMethod = HttpMethod.POST)
    public AjaxResponse save(@RequestBody CommonRequest request) {
        productService.saveOrUpdate(productConverter.convertToProduct(request));
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
