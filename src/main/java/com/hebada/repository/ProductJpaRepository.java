package com.hebada.repository;

import com.hebada.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by paddy on 2017/9/10.
 */
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCatalogId(long catalogId, Pageable pageable);
}
