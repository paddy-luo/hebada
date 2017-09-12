package com.hebada.converter;

import com.hebada.domain.Article;
import com.hebada.web.request.ArticleRequest;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Created by paddy on 2017/9/9.
 */
@Service
public class ArticleConverter {

  public Article convertToArticle(ArticleRequest request) {
    final Article article = new Article();
    article.setId(request.getId());
    article.setTitle(request.getTitle());
    article.setContent(request.getContent());
    article.setKeyWords(request.getKeyWords());
    article.setCatalogId(request.getCatalogId());
    article.setStatus(request.getStatus());
    Date now = new Date();
    if (article.getId() <= 0) {
      article.setCreatedTime(now);
      article.setUpdateTime(now);
    } else {
      article.setUpdateTime(now);
    }
    return article;
  }

  public Article convertToArticle(Article article, Article articleDomain) {
    articleDomain.setCatalogId(article.getCatalogId());
    articleDomain.setContent(article.getContent());
    articleDomain.setTitle(article.getTitle());
    articleDomain.setPublishTimed(article.getPublishTimed());
    articleDomain.setStatus(article.getStatus());
    articleDomain.setUpdateTime(new Date());
    return articleDomain;
  }
}
