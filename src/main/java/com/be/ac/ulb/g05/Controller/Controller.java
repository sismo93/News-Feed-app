package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;

public abstract class Controller  {

    protected ArticleService feed;

    public void setFeed(ArticleService feed){
        this.feed = feed;
    }


}
