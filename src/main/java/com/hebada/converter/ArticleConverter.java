package com.hebada.converter;

import com.hebada.domain.Article;
import com.hebada.entity.ArticleStatus;
import com.hebada.utils.ImageUtils;
import com.hebada.web.request.ArticleRequest;
import com.hebada.web.request.ArticleSearchRequest;
import com.hebada.web.request.PageRequest;
import com.hebada.web.response.ArticleListResponse;
import com.hebada.web.response.ArticleResponse;
import com.hebada.web.response.PageResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public Article convertToArticleSearch(PageRequest<ArticleSearchRequest> request) {
        Article articleQuery = new Article();
        if (request.getParams() == null) return articleQuery;
        articleQuery.setTitle(request.getParams().getTitle());
        articleQuery.setCatalogId(request.getParams().getCatalogId());
        articleQuery.setStatus(request.getParams().getStatus());
        return articleQuery;
    }

    public PageResponse<ArticleListResponse> convertToArticlePageResponse(Page<Article> articlePage, int currentPage, int pageSize) {
        PageResponse<ArticleListResponse> pageResponse = new PageResponse<ArticleListResponse>();
        pageResponse.setPageSize(pageSize);
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setTotal(articlePage.getTotalElements());
        pageResponse.setTotalPage(articlePage.getTotalPages());
        if (!articlePage.hasContent()) return pageResponse;
        for (Article article : articlePage.getContent())
            pageResponse.getContent().add(convertToArticleListResponse(article));
        return pageResponse;
    }

    public ArticleListResponse convertToArticleListResponse(Article article) {
        if (article == null) return null;
        ArticleListResponse response = new ArticleListResponse();
        response.setId(article.getId());
        response.setTitle(article.getTitle());
        if (!StringUtils.isEmpty(article.getArticlePageImageUrl()))
            response.setArticlePageImageUrl(article.getArticlePageImageUrl());
        else
            response.setArticlePageImageUrl(ImageUtils.getImageUrlFirstFromHtml(article.getContent()));
        String content = article.getContent();
        if (StringUtils.hasLength(content))
            response.setDescription(content.length() > 500 ? content.substring(0, 500) : content);
        response.setPublishTime(formatDate(article.getPublishTimed()));
        return response;
    }

    private String formatDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
