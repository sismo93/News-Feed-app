package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;


public class ArticleService extends Observable {

    private HashMap<String, Article> articles;

    public ArticleService() {
        articles = new HashMap<>();
    }

    public void addArticle(Article article){
        articles.put(article.getTitle(), article);
        notifyObservers();
    }

    public void deleteArticle(Article article){
        articles.remove(article.getTitle());
        notifyObservers();
    }

    public Article getArticle(String title){
        return articles.get(title);
    }

    public ArrayList<Article> getArticles(){
        return  new ArrayList<Article>(articles.values());
    }
}
