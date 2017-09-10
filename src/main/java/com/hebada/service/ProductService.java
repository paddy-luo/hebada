package com.hebada.service;

import com.hebada.domain.Product;
import com.hebada.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by paddy on 2017/9/10.
 */
@Service
public class ProductService {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Transactional
    public boolean saveOrUpdate(Product product) {
        if (product.getId() <= 0) {
            productJpaRepository.save(product);
            if (product.getId() > 0) return true;
            else return false;
        }
        productJpaRepository.save(product);
        return true;
    }

    @Transactional
    public void delete(long id) {
        productJpaRepository.delete(id);
    }

    public Page<Product> findAll(PageRequest pageRequest) {
        return productJpaRepository.findAll(pageRequest);
    }
}
