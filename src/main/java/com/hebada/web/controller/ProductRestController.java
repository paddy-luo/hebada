package com.hebada.web.controller;

import com.hebada.converter.PhotoConverter;
import com.hebada.converter.ProductConverter;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.PhotoService;
import com.hebada.service.ProductService;
import com.hebada.web.request.ProductRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by paddy on 2017/9/10.
 */
@RestController
@RequestMapping(value = URLs.PRODUCT)
@Api(basePath = URLs.PHOTO, description = "photo api")
public class ProductRestController {

    @Inject
    private ProductService productService;
    @Inject
    private PhotoService photoService;
    @Inject
    private PhotoConverter photoConverter;
    @Inject
    private ProductConverter productConverter;

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", notes = "save", httpMethod = HttpMethod.POST)
    public AjaxResponse save(@Validated @RequestBody ProductRequest request) {
        productService.save(productConverter.convertToProduct(request), request.getBigImageUrls(), request.getSmallImageUrls());
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.GET)
    @ApiOperation(value = "get", notes = "get", httpMethod = HttpMethod.GET)
    public AjaxResponse get(@PathVariable long id) {
        return AjaxResponse.ok().withData(productConverter.convertToProductResponse(productService.get(id)));
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.PUT)
    @ApiOperation(value = "update", notes = "update", httpMethod = HttpMethod.PUT)
    public AjaxResponse update(@PathVariable long id, @Validated @RequestBody ProductRequest request) {
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
}
