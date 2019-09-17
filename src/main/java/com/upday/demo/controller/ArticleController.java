package com.upday.demo.controller;



import com.upday.demo.controller.resources.ArticleResource;
import com.upday.demo.domain.model.Article;
import com.upday.demo.domain.port.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;


/**
 * Controller Article CRUD Operations
 */
@RestController
@Slf4j
@RequestMapping(value = "/news/v1")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;


    }

    /**
     *
     * @param articleResource
     * @return
     */
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ResponseEntity<Article> publishArticle(@RequestBody @Valid ArticleResource articleResource) {
        try {
            Article article = new Article(UUID.randomUUID().toString(),articleResource.getHeader(),articleResource.getDescription(),articleResource.getContent(),new Date(),articleResource.getAuthors(),articleResource.getTags());
            Article publishedArticle = articleService.save(article);
            return ResponseEntity.ok().body(publishedArticle);

        } catch (Exception e) {
            log.error("exception {]", e);
            ResponseEntity responseEntity = new ResponseEntity("Something Wrong Happened!! Please Contact our service agent", HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }

    /**
     *
     * @param article
     * @return
     */
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.PATCH)
    public ResponseEntity<Article> updateArticle(@PathVariable String articleId , @RequestBody @Valid ArticleResource article) {
        try {
            Article fetchOld = articleService.fetchArticle(articleId);

            updateArticle(article, fetchOld);

            Article publishedArticle = articleService.save(fetchOld);
            return ResponseEntity.ok().body(publishedArticle);

        } catch (Exception e) {
            log.error("exception {]", e);
            ResponseEntity responseEntity = new ResponseEntity("Something Wrong Happened!! Please Contact our service agent", HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public ResponseEntity<Article> getArticleById(@PathVariable String articleId ) {
        try {
            Article publishedArticle = articleService.fetchArticle(articleId);

            return ResponseEntity.ok().body(publishedArticle);

        } catch (Exception e) {
            log.error("exception {]", e);
            ResponseEntity responseEntity = new ResponseEntity("Something Wrong Happened!! Please Contact our service agent", HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }

    private void updateArticle(@Valid @RequestBody ArticleResource article, Article fetchOld) {
        if(!article.getContent().isEmpty()){
            fetchOld.setContent(article.getContent());
        }

        if(!article.getDescription().isEmpty()){
            fetchOld.setContent(article.getContent());
        }

        if(!article.getAuthors().isEmpty()){
            fetchOld.setAuthors(article.getAuthors());
        }

        if(!article.getHeader().isEmpty()){
            fetchOld.setHeader(article.getHeader());
        }

        if(!article.getTags().isEmpty()){
            fetchOld.setTags(article.getTags());
        }

    }

}
