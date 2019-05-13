package com.be.ac.ulb.infof307.g05;

import com.be.ac.ulb.g05.ParserWebSite;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static com.be.ac.ulb.g05.Controller.Router.showAlert;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
     * @author @OrestisTranganidas
     * 
     *@codereview @TanvirulHoque
     * 
     * @codereview @vtombou
     * 
     */

public class ParserWebSiteTest extends ApplicationTest {

    /**
     * Tests if the ParserArticle parses the article
     */
    @Test
    public void ParserArticleTest(){
        ParserWebSite web = new ParserWebSite();
        try {
            String article = web.ParserArticle("https://www.theguardian.com/world/2019/mar/28/bangladesh-high-rise-fire-dhaka-prompts-people-to-jump-from-building");
            assertFalse(article.equals(""));

            article = web.ParserArticle("https://www.theguardian.com/world/2019/mar/28/bangladesh-high-rise-fire-dhaka-prompts-people-to-jump-from-buildin");
            assertTrue(article.equals(""));
        } catch (IOException e) {
            showAlert("An error has occurred","Error");
        }
    }

    /**
     * Tests if the ParserImage parses the image
     */
    @Test
    public void ParserImageTest(){
        ParserWebSite web = new ParserWebSite();
        try {
            String image = web.ParserImage("https://www.theguardian.com/cities/2019/mar/28/kumbh-mela-cleaning-up-after-the-worlds-largest-human-gathering");
            assertFalse(image.equals(""));

            image = web.ParserImage("https://www.theguardian.com/cities/2019/mar/28/kumbh-mela-cleaning-up-after-the-worlds-largest-human-gatherin");
            assertTrue(image.equals(""));
        } catch (IOException e) {
            showAlert("An error has occurred","Error");
        }

    }

}