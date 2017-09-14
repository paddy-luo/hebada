package com.hebada.repository;

import com.hebada.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
public interface CatalogJpaRepository extends JpaRepository<Catalog, Long> {

    List<Catalog> findAllByParentId(long parent);

    void deleteAllByParentId(long parentId);

}
