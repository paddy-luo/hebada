package com.hebada.convertor;

import com.hebada.domain.Article;
import com.hebada.web.request.ArticleRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by paddy on 2017/9/9.
 */
@Service
public class ArticleConverter {

    public Article convertToArticle(ArticleRequest request) {
        final Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setKeyWords(request.getKeyWords());
        article.setCatalogId(request.getCatalogId());
        article.setStatus(request.getStatus());
        Date now = new Date();
        article.setCreatedTime(now);
        article.setUpdateTime(now);
        return article;
    }
}
