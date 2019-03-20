package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
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
                int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
                Article article = articles.get(selectedArticleIndex);

                try {
                    displayArticlePreview(article);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    /**
     * Called after scene loading
     * <p>
     * Init GUI
     * -
     * - fetch the content
     * - display the title of each article
     * -
     */
    @Override
    public void setupView() {
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }

    public void displayArticlePreview(Article article) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArticlePreview.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();

        ArticlePreviewController controller = fxmlLoader.<ArticlePreviewController>getController();
        controller.setArticle(article);

        stage.setTitle("Article preview");
        stage.setScene(scene);
        stage.show();
    }
}
