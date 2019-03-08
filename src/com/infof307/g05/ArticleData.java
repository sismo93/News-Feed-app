package com.infof307.g05;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * Controller of the Feed View
 * @author @MnrBn
 * @codereview @borsalinoK
 */
public class ArticleData {


    public ArrayList<String[]> articleData;

    /**
     * Singleton pattern
     */
    private static ArticleData instance;

    /**
     * Constructor
     */
    public ArticleData() {
        articleData = new ArrayList<>();
    }

    public static ArticleData Instance(){
        if(instance == null){
            instance = new ArticleData();
        }
        return instance;
    }

    /**
     * @param articles
     * store the articles in the article container
     */
    public void addArticle(String[] articles){
        articleData.add(articles);
    }

    /**
     * @return article from the container
     */
    public ArrayList<String[]> getArticleData() {
        return articleData;
    }
}
