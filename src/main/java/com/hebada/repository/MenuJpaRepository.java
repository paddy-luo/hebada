package com.hebada.repository;

import com.hebada.domain.MenuItem;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by paddy.luo on 2017/9/4.
 */
public interface MenuJpaRepository extends JpaRepository<MenuItem, Long> {
}
