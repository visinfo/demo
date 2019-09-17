package com.upday.demo;



import com.upday.demo.domain.model.Article;
import com.upday.demo.domain.model.Author;
import com.upday.demo.domain.port.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void before() {
        esTemplate.deleteIndex(Article.class);
        esTemplate.createIndex(Article.class);
        esTemplate.putMapping(Article.class);
        esTemplate.refresh(Article.class);
    }

    @Test
    public void testSave() {
        List<Author>  authors = new ArrayList<>();
        authors.add(new Author("Neeraj","aa"));
        List<String> tags = new ArrayList<>();
        tags.add("test");
        Article article = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", new Date(),authors,tags);
        Article testArticle = articleService.save(article);

        assertNotNull(testArticle.getId());
        assertEquals(testArticle.getHeader(), article.getHeader());
        assertEquals(testArticle.getAuthors(), article.getAuthors());
        assertEquals(testArticle.getPublishedAt(), article.getPublishedAt());

    }
    @Test
    public void testFindOne() {

        List<Author>  authors = new ArrayList<>();
        authors.add(new Author("Neeraj","aa"));
        List<String> tags = new ArrayList<>();
        tags.add("test");
        Article article = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", new Date(),authors,tags);
      articleService.save(article);

        Article testArticle = articleService.fetchArticle(article.getId());


        assertNotNull(testArticle.getId());
        assertEquals(testArticle.getHeader(), article.getHeader());
        assertEquals(testArticle.getAuthors(), article.getAuthors());
        assertEquals(testArticle.getPublishedAt(), article.getPublishedAt());

    }

    @Test
    public void testFindByAuthor() {
        List<Author>  authors = new ArrayList<>();
        Author author =new Author("Neeraj","aa");
        authors.add(author);
        List<String> tags = new ArrayList<>();
        tags.add("test");
        Article article = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", new Date(),authors,tags);
        articleService.save(article);
        Page<Article> testArticle = articleService.fetchArticleByAuthor(author.getName(), new PageRequest(0,10).first());
        assertEquals(testArticle.getContent().get(0).getAuthors().get(0).getName(), author.getName());
    }

    @Test
    public void testDelete() {

        List<Author>  authors = new ArrayList<>();
        Author author =new Author("Neeraj","aa");
        authors.add(author);
        List<String> tags = new ArrayList<>();
        tags.add("test");
        Article article = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", new Date(),authors,tags);
        articleService.save(article);
        articleService.delete(article);
        Article testArticle = articleService.fetchArticle(article.getId());
        assertNull(testArticle);
    }
    @Test
    public void testFromDateToDate() {

        List<Author>  authors = new ArrayList<>();
        Author author =new Author("Neeraj","aa");
        authors.add(author);
        List<String> tags = new ArrayList<>();
        tags.add("test");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -3);
        date = c.getTime();

        Article article = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", date,authors,tags);
        articleService.save(article);
        Article article2 = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", new Date(),authors,tags);
        articleService.save(article2);


        List<Article> testArticle = articleService.fetchArticleByPublishedAt(date,new Date());
        assertEquals(testArticle.size(),2);
    }
    @Test
    public void testFetchByTag() {

        List<Author>  authors = new ArrayList<>();
        Author author =new Author("Neeraj","aa");
        authors.add(author);
        List<String> tags = new ArrayList<>();
        tags.add("test");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -3);
        date = c.getTime();

        Article article = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", date,authors,tags);
        articleService.save(article);
        List<String> tags1 = new ArrayList<>();
        tags1.add("test1");
        Article article2 = new Article(UUID.randomUUID().toString(), "News one","Test desciption","test content", new Date(),authors,tags1);

        articleService.save(article2);


        Page<Article> testArticle = articleService.fetchArticleByTag("test1",new PageRequest(0,10));
        assertEquals(testArticle.getContent().size(),1);
    }

}
