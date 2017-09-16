package com.hebada.repository;

import com.hebada.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by paddy on 2017/9/9.
 */
public interface ArticleJpaRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor {
    
}
