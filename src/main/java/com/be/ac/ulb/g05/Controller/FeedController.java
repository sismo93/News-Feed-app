package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Controller of the ArticleService View
 *
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class FeedController extends Controller implements Observer {

    /**
     * ArticleViewField displays the content on the page
     */
    public VBox articleContainer;

    /**
     * Called after scene loading
     * <p>
     * Init GUI
     * -
     * - fetch the content
     * - display the title of each article
     * -
     */

    @FXML
    public void initialize() {

        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);

    }


    private void pushToArticleView(ArrayList<Article> articles){

        articleContainer.getChildren().clear();

        ObservableList<String> names = FXCollections.observableArrayList();
        articles.forEach(article -> {
            names.add(article.getTitle() + "\n\n" + article.getAuthor());
        });
        ListView<String> listView = new ListView<String>(names);
        listView.setFixedCellSize(100);
        articleContainer.getChildren().add(listView);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
                //Action pour ouvrir la selection
                int temp = listView.getSelectionModel().getSelectedIndex();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, temp + newvalue, ButtonType.OK);
                alert.showAndWait();
            }
        });
    }


    /**
     * @param actionEvent Back to menu
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }
}
