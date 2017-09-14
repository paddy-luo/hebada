package com.hebada.converter;

import com.hebada.domain.Product;
import com.hebada.web.request.CommonRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by paddy on 2017/9/11.
 */
@Service
public class ProductConverter {

    public Product convertToProduct(CommonRequest request) {
        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        final Date now = new Date();
        if (request.getId() > 0) {
            product.setUpdateTime(now);
        } else {
            product.setCreatedTime(now);
            product.setUpdateTime(now);
        }
        return product;
    }

    public Product convertToProduct(Product product, Product productDomain) {
        productDomain.setName(product.getName());
        productDomain.setDescription(product.getDescription());
        productDomain.setImageUrl(product.getImageUrl());
        productDomain.setUpdateTime(new Date());
        return productDomain;
    }
}
