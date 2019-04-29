package com.be.ac.ulb.infof307.g05;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.RSSFeedParser;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * author @otrangan
 */
public class ArticleTest extends ApplicationTest {

    Article article = new Article();

    /**
     * Tests if the setImage and getImage functions work properly
     */
    @Test
    public void getImageTest(){
        article.setImage("Image");
        assertTrue(article.getImage().equals("Image"));
    }

    /**
     * Tests if the setPubDate and getPubDate functions work properly
     */
    @Test
    public void getPubDateTest(){
        article.setPubDate("PubDate");
        assertTrue(article.getPubDate().equals("PubDate"));
    }

    /**
     * Tests if the setDefaultThumbnail and getDefaultThumbnail functions work properly
     */
    @Test
    public void getDefaultThumbnailTest(){
        article.setDefaultThumbnail("DefaultThumbnail");
        assertTrue(article.getDefaultThumbnail().equals("DefaultThumbnail"));
    }

    /**
     * Tests if the setTitle and getTitle functions work properly
     */
    @Test
    public void getTitleTest(){
        article.setTitle("Title");
        assertTrue(article.getTitle().equals("Title"));
    }

    /**
     * Tests if the setDescription and getDescription functions work properly
     */
    @Test
    public void getDescriptionTest(){
        article.setDescription("Description");
        assertTrue(article.getDescription().equals("Description"));
    }

    /**
     * Tests if the setLink and getLink functions work properly
     */
    @Test
    public void getLinkTest(){
        article.setLink("Link");
        assertTrue(article.getLink().equals("Link"));
    }

    /**
     * Tests if the setContent and getContent functions work properly
     */
    @Test
    public void getContentTest(){
        article.setContent("Content");
        assertTrue(article.getContent().equals("Content"));
    }
}