package com.hebada.repository;

import com.hebada.domain.News;
import com.hebada.enumeration.NewsType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by paddy on 2017/9/3.
 */
public interface NewsJpaRepository extends JpaRepository<News, Long> {

  Page<News> findByTitleOrNewsType(String title, NewsType newsType, Pageable pageable);
}
