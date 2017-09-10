package com.hebada.repository;

import com.hebada.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by paddy.luo on 2017/9/4.
 */
public interface CatalogJpaRepository extends JpaRepository<Catalog, Long> {
}
