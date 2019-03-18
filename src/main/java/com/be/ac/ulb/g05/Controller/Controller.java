package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;

public abstract class Controller  {

    protected ArticleService articleService;

    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }


}
