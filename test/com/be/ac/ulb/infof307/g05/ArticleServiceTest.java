package com.be.ac.ulb.infof307.g05;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.RSSFeedParser;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArticleServiceTest extends ApplicationTest {
    RSSFeedParser feeder = new RSSFeedParser("https://www.theguardian.com/uk/business/rss");
    ArticleService feed = new ArticleService();

    /**
     * Tests if addArticle adds the article correctly
     */
    @Test
    public void addArticleTest(){
        ArrayList<Article> articles = feeder.readRSS();
        feed.addArticle(articles.get(0));
        ArrayList<Article> temp = feed.getArticles();
        assertTrue(temp.contains(articles.get(0)));
    }

    /**
     * Tests if deleteArticle deletes the article correctly
     */
    @Test
    public void deleteArticleTest(){
        ArrayList<Article> articles = feeder.readRSS();
        feed.addArticle(articles.get(0));
        feed.deleteArticle(articles.get(0));
        ArrayList<Article> temp = feed.getArticles();
        assertFalse(temp.contains(articles.get(0)));
    }

    /**
     * Tests if reset resets the list correctly
     */
    @Test
    public void resetTest(){
        ArrayList<Article> articles = feeder.readRSS();
        feed.addArticle(articles.get(0));
        feed.reset();
        ArrayList<Article> temp = feed.getArticles();
        assertFalse(temp.contains(articles.get(0)));
    }

}