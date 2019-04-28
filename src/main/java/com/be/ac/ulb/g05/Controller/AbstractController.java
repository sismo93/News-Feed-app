package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;


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
     * Sets the article service
     * @param articleService article service
     */
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    /**
     * Sets up the view
     */
    public void setupView(){ }

    public void injectDependencies(DependencyInjector dependencyInjector) {
        this.articleService = dependencyInjector.getArticleService();
    }

    public void onActive(){

    }
}
