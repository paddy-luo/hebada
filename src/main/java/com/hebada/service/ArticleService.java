package com.hebada.service;

import com.hebada.converter.ArticleConverter;
import com.hebada.domain.Article;
import com.hebada.entity.ArticleStatus;
import com.hebada.repository.ArticleJpaRepository;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public void update(long id, Article article) {
        Article articleDomain = this.findOne(id);
        if (articleDomain == null) return;
        articleJpaRepository.save(articleConverter.convertToArticle(article, articleDomain));
    }

    @Transactional
    public void delete(long id) {
        articleJpaRepository.delete(id);
    }

    public Article findOne(long id) {
        return articleJpaRepository.findOne(id);
    }

    public Page<Article> findArticles(Article article, PageRequest request) {
        return articleJpaRepository.findAll(where(article.getTitle()
            , article.getCatalogId(), article.getStatus()), request);
    }


    private Specification<Article> where(final String title, final long catalogId, final ArticleStatus status) {
        return new Specification<Article>() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicates = cb.conjunction();
                if (StringUtils.hasLength(title))
                    predicates.getExpressions().add(cb.like(root.get("title"), "%" + title + "%"));
                if (catalogId > 0)
                    predicates.getExpressions().add(cb.equal(root.get("catalogId"), catalogId));
                if (status != null)
                    predicates.getExpressions().add(cb.equal(root.get("status"), status));
                query.orderBy(cb.desc(root.get("id")));
                return predicates;
            }
        };
    }
}
