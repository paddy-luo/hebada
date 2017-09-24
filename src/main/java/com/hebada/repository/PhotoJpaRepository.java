package com.hebada.repository;

import com.hebada.domain.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by paddy.luo on 2017/9/19.
 */
public interface PhotoJpaRepository extends JpaRepository<Photo, Long>, JpaSpecificationExecutor {

    Page<Photo> findByCatalogId(long catalogId, Pageable pageable);

    List<Photo> findByProductIdOrderByIdAsc(long productId);
}
