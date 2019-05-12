package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.Model.TwitterService;


/**
 * @author @borsalinoK
 * @codereview @MnrBn
 * Abstract class that allow us to use the same List of article through all the views
 */
public abstract class AbstractController {

    /**
     * Article service
     */
    protected  ArticleService articleService;

    /**
     * Twitter Service
     */
    protected TwitterService twitterService;

    /**
     * Sets the article service
     * @param articleService article service
     */
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }


    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    public void setupView(){ }

    /**
     * @param dependencyInjector object responsible for delivering the dependency
     * called on initialization
     * link the client to his specific services
     */
    public void injectDependencies(DependencyInjector dependencyInjector) {
        this.articleService = dependencyInjector.getArticleService();
        this.twitterService = dependencyInjector.getTwitterService();
    }


    /**
     * Called whenever whenever the focus is on the view managed by the controller
     *
     */
    public void onActive(){

    }
}
