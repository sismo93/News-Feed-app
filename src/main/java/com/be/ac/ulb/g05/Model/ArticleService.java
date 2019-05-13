package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author @borsalinoK
 * @codereview @mouscb
 * <p>
 * Class that allow us to store Article object
 * we can add/delete article from the list.
 * with this class we will have an article list accessible in all the views
 */
public class ArticleService {

    /**
     * Article HashMap
     */
    private HashMap<String, Article> articles;

    /**
     * Article object
     */
    private Article article;

    /**
     * Constructor
     */
    public ArticleService() {
        articles = new HashMap<>();
    }

    /**
     * Adds article into HashMap
     *
     * @param article object
     */
    public void addArticle(Article article) {
        articles.put(article.getTitle(), article);
    }

    /**
     * Deletes article from HashMap
     *
     * @param article object
     */
    public void deleteArticle(Article article) {
        articles.remove(article.getTitle());
    }

    /**
     * List of Articles
     *
     * @return list of articles
     */
    public ArrayList<Article> getArticleAll() {
        return new ArrayList<>(articles.values());
    }

    /**
     * Article getter
     *
     * @return article object
     */
    public Article getArticle() {
        return article;
    }

    /**
     * Article setter
     *
     * @param article object
     */
    public void selectArticle(Article article) {
        this.article = article;
    }

    /**
     * Clears all articles from feed
     */
    public void reset() {
        articles.clear();
    }
}
