package be.ac.ulb.infof307.g05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * Controller of the Feed View
 * @author @MnrBn
 * @codereview @borsalinoK
 */
public class ArticleData {


    public ObservableList<String[]> articleData;

    /**
     * Singleton pattern
     */
    private static ArticleData instance;

    /**
     * Constructor
     */
    public ArticleData() {
        articleData = FXCollections.observableList( new ArrayList<>());
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
    public ObservableList<String[]> getArticleData() {
        return articleData;
    }
}
