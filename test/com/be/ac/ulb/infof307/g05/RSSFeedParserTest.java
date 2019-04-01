package com.be.ac.ulb.infof307.g05;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.RSSFeedParser;
import junit.framework.TestCase;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RSSFeedParserTest extends ApplicationTest {

    /**
     * Tests if the RSSFeedParser returns a list of articles
     */
    @Test
    public void ReadRSSTest(){
        String[] websites = {"http://sport24.lefigaro.fr/rssfeeds/sport24-accueil.xml", "https://www.lemonde.fr/m-actu/rss_full.xml",
                "https://www.lepoint.fr/24h-infos/rss.xml", "https://www.theguardian.com/uk/business/rss",
                "https://feeds.feedburner.com/rtlinfo/belgique"};
        for(int i=0;i<5;++i){
            RSSFeedParser parser = new RSSFeedParser(websites[i]);
            ArrayList<Article> articles = parser.readRSS();
            assertTrue(articles.size()>0);
        }
    }
}