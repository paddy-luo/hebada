package com.hebada.web.controller;

import com.hebada.converter.PhotoConverter;
import com.hebada.converter.ProductConverter;
import com.hebada.domain.Photo;
import com.hebada.domain.Product;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.PhotoService;
import com.hebada.service.ProductService;
import com.hebada.web.interceptor.LoginRequired;
import com.hebada.web.request.ProductRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@LoginRequired
public class ProductRestController {

    @Inject
    private ProductService productService;
    @Inject
    private ProductConverter productConverter;
    @Inject
    private PhotoService photoService;
    @Inject
    private PhotoConverter photoConverter;

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", notes = "save", httpMethod = HttpMethod.POST)
    public AjaxResponse save(@Validated @RequestBody ProductRequest request) {
        productService.save(productConverter.convertToProduct(request));
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.GET)
    @ApiOperation(value = "get", notes = "get", httpMethod = HttpMethod.GET)
    @LoginRequired(required = false)
    public AjaxResponse get(@PathVariable long id) {
        Product product = productService.get(id);
        if (product == null) return AjaxResponse.ok();
        List<Photo> photoList = photoService.findPhotoListByProductId(product.getId());
        List<Map<String, String>> imageUrls = photoConverter.convertToProductImageUrls(photoList);
        return AjaxResponse.ok().withData(productConverter.convertToProductResponse(product, imageUrls));
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.PUT)
    @ApiOperation(value = "update", notes = "update", httpMethod = HttpMethod.PUT)
    public AjaxResponse update(@PathVariable long id, @Validated @RequestBody ProductRequest request) {
        productService.update(id, productConverter.convertToProduct(request));
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", notes = "delete", httpMethod = HttpMethod.DELETE)
    public AjaxResponse delete(@PathVariable long id) {
        productService.delete(id);
        return AjaxResponse.ok();
    }
}
