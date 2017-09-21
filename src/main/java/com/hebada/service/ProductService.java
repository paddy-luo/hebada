package com.hebada.service;

import com.hebada.converter.ProductConverter;
import com.hebada.domain.Product;
import com.hebada.repository.ProductJpaRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by paddy on 2017/9/10.
 */
@Service
public class ProductService {

    @Inject
    private ProductJpaRepository productJpaRepository;
    @Inject
    private ProductConverter productConverter;

    public Product get(long id) {
        return productJpaRepository.findOne(id);
    }

    @Transactional
    public void save(Product product) {
        productJpaRepository.save(product);
    }

    @Transactional
    public void update(Product product) {
        productJpaRepository.save(productConverter.convertToProduct(product, this.get(product.getId())));
    }

    @Transactional
    public void delete(long id) {
        productJpaRepository.delete(id);
    }

    public Page<Product> findAllByCatalogId(long catalogId, int currentPage, int pageSize) {
        return productJpaRepository.findAllByCatalogId(catalogId, new PageRequest(currentPage, pageSize));
    }
}
