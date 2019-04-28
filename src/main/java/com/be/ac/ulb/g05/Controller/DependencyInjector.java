package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.Model.TwitterService;

/**
 * @author @borsalinoK
 * @codereview @tanvir.hoque
 * Injector used by the client that need custom dependencies
 * is responsible of setting up the services and delivering it to the client
 */
public class DependencyInjector {


    /**
     * Service responsible for manipulating rss, consulting and other related operations
     */
    private ArticleService articleService;
    /**
     * Service responsible for authenticating, posting, querying twitter services and other related operations
     */
    private TwitterService twitterService;

    public DependencyInjector() {
        this.articleService = new ArticleService();
        this.twitterService = new TwitterService();
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public TwitterService getTwitterService(){
        return twitterService;
    }
}
