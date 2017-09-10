package com.hebada.service;

import com.hebada.domain.Article;
import com.hebada.repository.ArticleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by paddy on 2017/9/9.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleJpaRepository articleJpaRepository;

    @Transactional
    public boolean saveOrUpdate(Article article) {
        if (article.getId() <= 0) {
            articleJpaRepository.save(article);
            return true;
        }
        Article articleDomain = articleJpaRepository.getOne(article.getId());
        articleDomain.setCatalogId(article.getCatalogId());
        articleDomain.setContent(article.getContent());
        articleDomain.setTitle(article.getTitle());
        articleDomain.setPublishTimed(article.getPublishTimed());
        articleDomain.setStatus(article.getStatus());
        articleDomain.setUpdateTime(new Date());
        articleJpaRepository.save(articleDomain);
        return true;
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
}
