package com.hebada.converter;

import com.hebada.domain.Product;
import com.hebada.web.request.ProductRequest;
import com.hebada.web.response.PageResponse;
import com.hebada.web.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by paddy on 2017/9/11.
 */
@Service
public class ProductConverter {

    public Product convertToProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setCatalogId(request.getCatalogId());
        product.setDescription(request.getDescription());
        product.setContent(request.getContent());
        final Date now = new Date();
        product.setUpdateTime(now);
        return product;
    }

    public Product convertToProduct(Product product, Product productDomain) {
        productDomain.setName(product.getName());
        productDomain.setDescription(product.getDescription());
        productDomain.setContent(product.getContent());
        productDomain.setCatalogId(product.getCatalogId());
        productDomain.setImageUrl(product.getImageUrl());
        productDomain.setUpdateTime(new Date());
        return productDomain;
    }

    public ProductResponse convertToProductResponse(Product product) {
        if (product == null) return null;
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setContent(product.getContent());
        response.setImageUrl(product.getImageUrl());
        return response;
    }

    public PageResponse<ProductResponse> convertToProductResponse(Page<Product> productPage, int currentPage, int pageSize) {
        PageResponse<ProductResponse> pageResponse = new PageResponse<ProductResponse>();
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setTotalPage(productPage.getTotalPages());
        pageResponse.setPageSize(pageSize);
        if (!productPage.hasContent()) return pageResponse;
        for (Product product : productPage.getContent()) {
            pageResponse.getContent().add(convertToProductResponse(product));
        }
        return pageResponse;
    }
}
