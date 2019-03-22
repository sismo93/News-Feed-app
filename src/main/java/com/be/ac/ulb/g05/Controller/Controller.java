package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;

/**
 * @author @borsalinoK
 * @codereview @MnrBn
 * Abstract class that allow us to use the same List of article through all the views
 */
public abstract class Controller  {

    protected ArticleService articleService;

    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    public void setupView(){ }

}
