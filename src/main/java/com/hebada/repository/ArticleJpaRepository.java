package com.hebada.repository;

import com.hebada.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by paddy on 2017/9/9.
 */
public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

    Page<Article> findByCatalogId(long catalogId, Pageable pageable);

    // todo: 获取五条新闻、公告
    List<Article> findTop5ById(long id);

    List<Article> findArticlesByCatalogIdAndPublishTimedExists(long catalogId);

    List<Article> findArticlesByCatalogId(long catalogId);
}
