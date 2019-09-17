package com.upday.demo.controller;


import com.upday.demo.domain.model.Article;
import com.upday.demo.domain.port.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * Controller to fetch Articles for Different query Parameters
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/news/v1")
public class ArticleFilterController {
    private final ArticleService articleService;

    @Autowired
    public ArticleFilterController(ArticleService articleService) {
        this.articleService = articleService;


    }

    @RequestMapping(value = "/article/getByTag", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getArticleByTag(@RequestParam ("tag") String tag, Pageable pageable ) {
        try {


            Page<Article> publishedArticles = articleService.fetchArticleByTag(tag,pageable);

            return ResponseEntity.ok().body(publishedArticles.getContent());

        } catch (Exception e) {
            log.error("exception {]", e);
            ResponseEntity responseEntity = new ResponseEntity("Something Wrong Happened!! Please Contact our service agent", HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }
    @RequestMapping(value = "/article/getByAuthor", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getArticleByAuthor(@RequestParam ("author") String author, Pageable pageable ) {
        try {


            Page<Article> publishedArticles = articleService.fetchArticleByAuthor(author,pageable);

            return ResponseEntity.ok().body(publishedArticles.getContent());

        } catch (Exception e) {
            log.error("exception {]", e);
            ResponseEntity responseEntity = new ResponseEntity("Something Wrong Happened!! Please Contact our service agent", HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }

    @RequestMapping(value = "/article/getByPublishedDate", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getArticleByPublishedDate(@RequestParam ("fromDate") String fromDate, @RequestParam ("toDate") String toDate,Pageable pageable ) {
        try {

            Date fDate=new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            Date tDate=new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
            List<Article> publishedArticles = articleService.fetchArticleByPublishedAt(fDate,tDate);

            return ResponseEntity.ok().body(publishedArticles);

        } catch (Exception e) {
            log.error("exception {]", e);
            ResponseEntity responseEntity = new ResponseEntity("Something Wrong Happened!! Please Contact our service agent", HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }
}
