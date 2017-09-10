package com.hebada.web.controller;

import com.hebada.domain.Product;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.ProductService;
import com.hebada.web.request.CommonRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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


    //todo:开发到这里
    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", notes = "save", httpMethod = HttpMethod.POST)
    public AjaxResponse save(@RequestBody CommonRequest request) {
        productService.saveOrUpdate(new Product());
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", notes = "delete", httpMethod = HttpMethod.DELETE)
    public AjaxResponse delete(@PathVariable long id) {
        productService.delete(id);
        return AjaxResponse.ok();
    }
}
