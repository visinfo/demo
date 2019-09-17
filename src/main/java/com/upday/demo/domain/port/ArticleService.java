package com.upday.demo.domain.port;


import com.upday.demo.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ArticleService {

    Article save(Article article) ;

    void delete(Article article);

    Article fetchArticle(String articleId);

    Page<Article> fetchArticleByAuthor(String authorName, Pageable pageable);

    List<Article> fetchArticleByPublishedAt(Date fromDate, Date toDate);

    Page<Article> fetchArticleByTag(String tag, Pageable pageable);




}
