package com.hebada.converter;

import com.google.common.collect.Lists;
import com.hebada.domain.Article;
import com.hebada.entity.ArticleStatus;
import com.hebada.utils.ImageUtils;
import com.hebada.web.request.ArticleRequest;
import com.hebada.web.request.ArticleSearchRequest;
import com.hebada.web.response.ArticleResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        article.setCreatedTime(now);
        article.setUpdateTime(now);
        if (ArticleStatus.PUBLISHED.equals(request.getStatus())) {
            article.setPublishTimed(now);
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

    public List<ArticleResponse> convertToArticleResponse(List<Article> articleList) {
        if (CollectionUtils.isEmpty(articleList)) return null;
        List<ArticleResponse> responseList = Lists.newArrayList();
        for (Article article : articleList) {
            responseList.add(convertToArticleResponse(article));
        }
        return responseList;
    }

    public ArticleResponse convertToArticleResponse(Article article) {
        if (article == null) return null;
        ArticleResponse response = new ArticleResponse();
        response.setId(article.getId());
        response.setKeyWords(article.getKeyWords());
        response.setTitle(article.getTitle());
        response.setArticlePageImageUrl(ImageUtils.getImageUrlFirstFromHtml(article.getContent()));
        response.setContent(article.getContent());
        response.setAuthor(article.getAuthor());
        response.setStatus(article.getStatus());
        response.setCreateTime(formatDate(article.getCreatedTime()));
        response.setPublishTime(formatDate(article.getPublishTimed()));
        return response;
    }

    public Article convertToArticleSearch(ArticleSearchRequest request) {
        Article articleQuery = new Article();
        articleQuery.setTitle(request.getTitle());
        articleQuery.setCatalogId(request.getCatalogId());
        articleQuery.setStatus(request.getStatus());
        return articleQuery;
    }

    private String formatDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
