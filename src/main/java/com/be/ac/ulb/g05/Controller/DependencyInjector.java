package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.Model.TwitterService;

/**
 * @author @borsalinoK
 * @codereview @tanvir.hoque
 * Allow us to not use switch case
 */
public class DependencyInjector {


    private ArticleService articleService;
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
