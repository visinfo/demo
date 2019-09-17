package com.upday.demo.repository;


import com.upday.demo.domain.model.Article;
import com.upday.demo.domain.port.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleRepository implements ArticleService {

    private final ArticleElastichSearchRepository articleElastichSearchRepository;

    @Autowired
    public ArticleRepository(ArticleElastichSearchRepository articleElastichSearchRepository) {
        this.articleElastichSearchRepository = articleElastichSearchRepository;
    }

    @Override
    public Article save(Article article) {
        return articleElastichSearchRepository.save(article);
    }

    @Override
    public void delete(Article article) {
        articleElastichSearchRepository.delete(article);
    }

    @Override
    public Article fetchArticle(String articleId) {
        return articleElastichSearchRepository.findOne(articleId);
    }

    @Override
    public Page<Article> fetchArticleByAuthor(String authorName, Pageable pageable) {
        return articleElastichSearchRepository.findByAuthorsName(authorName, pageable);
    }

    @Override
    public List<Article> fetchArticleByPublishedAt(Date fromDate, Date toDate) {
        return articleElastichSearchRepository.findByPublishedAtBetween(fromDate,toDate);
    }

    @Override
    public Page<Article> fetchArticleByTag(String tag, Pageable pageable) {
        return articleElastichSearchRepository.findByFilteredTagQuery(tag,pageable);
    }
}
