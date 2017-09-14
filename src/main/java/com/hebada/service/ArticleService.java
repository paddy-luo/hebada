package com.hebada.service;

import com.hebada.converter.ArticleConverter;
import com.hebada.domain.Article;
import com.hebada.repository.ArticleJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by paddy on 2017/9/9.
 */
@Service
public class ArticleService {

    @Inject
    private ArticleJpaRepository articleJpaRepository;
    @Inject
    private ArticleConverter articleConverter;

    @Transactional
    public boolean save(Article article) {
        articleJpaRepository.save(article);
        if (article.getId() > 0) return true;
        return false;
    }

    @Transactional
    public void update(Article article) {
        articleJpaRepository.save(articleConverter.convertToArticle(article, this.findOne(article.getId())));
    }

    @Transactional
    public void delete(long id) {
        articleJpaRepository.delete(id);
    }

    public Article findOne(long id) {
        return articleJpaRepository.findOne(id);
    }

    public Page<Article> findByCatalogId(long catalogId, PageRequest request) {
        return articleJpaRepository.findByCatalogId(catalogId, request);
    }

    public List<Article> findTop5ByCatalogId(long id) {
        return articleJpaRepository.findTop5ById(id);
    }

    public List<Article> findArticlesByCatalogId(long catalogId) {
        return articleJpaRepository.findArticlesByCatalogId(catalogId);
    }
}
