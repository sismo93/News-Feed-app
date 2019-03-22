package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;


/**
 * @author @borsalinoK
 * @codereview @mouscb
 * Class that allow us to store Article object
 * we can add/delete article from the list.
 * with this class we will have an article list accessible in all the views
 */
public class ArticleService extends Observable {

    private HashMap<String, Article> articles;
    private Article article;

    public ArticleService() {
        articles = new HashMap<>();
    }

    public void addArticle(Article article){
        setChanged();
        articles.put(article.getTitle(), article);
        notifyObservers();
    }

    public void deleteArticle(Article article){
        setChanged();
        articles.remove(article.getTitle());
        notifyObservers();
    }


    public ArrayList<Article> getArticles(){
        return new ArrayList<>(articles.values());
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
