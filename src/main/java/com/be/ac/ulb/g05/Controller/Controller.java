package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;

public abstract class Controller  {

    private Article article;
    protected ArticleService articleService;

    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }

    public void setupView(){ }

    public void passArticle(Article article) {
        this.article = article;
    }
}
