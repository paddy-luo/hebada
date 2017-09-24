package com.hebada.converter;

import com.google.common.collect.Lists;
import com.hebada.domain.Photo;
import com.hebada.domain.Product;
import com.hebada.web.request.ProductRequest;
import com.hebada.web.response.PageResponse;
import com.hebada.web.response.ProductListResponse;
import com.hebada.web.response.ProductResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by paddy on 2017/9/11.
 */
@Service
public class ProductConverter {

    public Product convertToProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setCatalogId(request.getCatalogId());
        product.setAlcoholicStrength(request.getAlcoholicStrength());
        product.setStandard(request.getStandard());
        product.setDescription(request.getDescription());
        product.setContent(request.getContent());
        product.setDescription(request.getDescription());
        product.setRecommended(request.isRecommended());
        final Date now = new Date();
        product.setCreatedTime(now);
        product.setUpdateTime(now);
        return product;
    }

    public Product convertToProduct(Product product, Product productDomain) {
        productDomain.setName(product.getName());
        productDomain.setContent(product.getContent());
//        productDomain.setCatalogId(product.getCatalogId());
        productDomain.setAlcoholicStrength(product.getAlcoholicStrength());
        productDomain.setStandard(product.getStandard());
        productDomain.setDescription(product.getDescription());
        productDomain.setRecommended(product.isRecommended());
        productDomain.setUpdateTime(new Date());
        return productDomain;
    }

    public ProductResponse convertToProductResponse(Product product, List<Map<String, String>> bigImageUrls) {
        if (product == null) return null;
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setAlcoholicStrength(product.getAlcoholicStrength());
        response.setStandard(product.getStandard());
        response.setDescription(product.getDescription());
        response.setContent(product.getContent());
        response.setRecommended(product.isRecommended());
        response.setCatalogId(product.getCatalogId());
        if (CollectionUtils.isNotEmpty(bigImageUrls))
            response.getProductImageUrls().addAll(bigImageUrls);
        return response;
    }

    public PageResponse<ProductListResponse> convertToProductListResponse(Page<Product> productPage, int currentPage, int pageSize, Map<Long, Photo> photoMap) {
        PageResponse<ProductListResponse> pageResponse = new PageResponse<ProductListResponse>();
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setTotalPage(productPage.getTotalPages());
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(productPage.getTotalElements());
        if (!productPage.hasContent()) return pageResponse;
        for (Product product : productPage.getContent()) {
            pageResponse.getContent().add(convertToProductListResponse(product, photoMap));
        }
        return pageResponse;
    }

    public List<Long> convertToProductIds(List<Product> productList) {
        if (CollectionUtils.isEmpty(productList)) return null;
        List<Long> productIds = Lists.newArrayList();
        for (Product product : productList) productIds.add(product.getId());
        return productIds;
    }

    private ProductListResponse convertToProductListResponse(Product product, Map<Long, Photo> photoMap) {
        ProductListResponse productListResponse = new ProductListResponse();
        productListResponse.setId(product.getId());
        productListResponse.setName(product.getName());
        productListResponse.setAlcoholicStrength(product.getAlcoholicStrength());
        productListResponse.setStandard(product.getStandard());
        productListResponse.setDescription(product.getDescription());
        if (photoMap == null || photoMap.size() == 0) return productListResponse;
        Photo photo = photoMap.get(product.getId());
        if (photo != null)
            productListResponse.setProductImageUrl(photo.getBigImageUrl());
        return productListResponse;
    }
}
