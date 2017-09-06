package com.hebada.service;

import com.hebada.domain.News;
import com.hebada.enumeration.NewsType;
import com.hebada.repository.NewsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by paddy on 2017/9/3.
 */
@Service
public class NewsService {

    @Autowired
    private NewsJpaRepository newsJpaRepository;

    public News findById(long id) {
        return newsJpaRepository.findOne(id);
    }

    @Transactional
    public boolean saveOrUpdate(News news) {
        News newsDomain = newsJpaRepository.save(news);
        if (newsDomain != null && news.getId() > 0) return true;
        else return false;
    }

    @Transactional
    public void deleteById(long id) {
        newsJpaRepository.delete(id);
    }

    public Page<News> findByTitleOrNewsType(String title, NewsType newsType, PageRequest request) {
        return newsJpaRepository.findByTitleOrNewsType(title, newsType, request);
    }
}
