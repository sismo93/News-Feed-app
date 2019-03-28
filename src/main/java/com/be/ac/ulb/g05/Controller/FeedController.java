package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.Controller.Router.*;
import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static com.be.ac.ulb.g05.Controller.ArticleViewController.saveImage;

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
     * @param articles
     * Function that allow us to display the picture + the information about the article
     */
    private void pushToArticleView(ArrayList<Article> articles) {
        articleContainer.getChildren().clear();

        ObservableList<PreviewThumbnailCell> thumbnailList = FXCollections.observableArrayList();

        articles.forEach(article -> {
            ImageView imageView = new ImageView();
            try {
                saveImage(article.getDefaultThumbnail(), "file:temp-feedbuzz-v.jpg", imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PreviewThumbnailCell previewThumbnail = new PreviewThumbnailCell(imageView, article.getTitle(),
                    article.getPubDate(), article.getGeolocation(),article.getSource());
            thumbnailList.add(previewThumbnail);
        });

        ListView<PreviewThumbnailCell> listView = new ListView<>(thumbnailList);
        listView.setFixedCellSize(150);

        listView.setCellFactory(new Callback<ListView<PreviewThumbnailCell>, ListCell<PreviewThumbnailCell>>() {
            @Override
            public ListCell<PreviewThumbnailCell> call(ListView<PreviewThumbnailCell> param) {
                return new ListCell<PreviewThumbnailCell>(){

                    @Override
                    protected void updateItem(PreviewThumbnailCell pr, boolean empty){
                        super.updateItem(pr, empty);

                        if(pr != null){
                            setGraphic(pr.getImage());
                            setText(pr.getTitle() + "\n\n" +"Date : " +pr.getDate() +
                            "\n" + "Source : " + pr.getSource() + "\n" + "Localisation : " +
                            pr.getLocalisation());
                        }
                    }
                };
            }
        });

        articleContainer.getChildren().add(listView);

        listView.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
            Article article = articles.get(selectedArticleIndex);

            try {
                displayArticlePreview(article);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        articleService.addObserver(this);
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }

    public void displayArticlePreview(Article article) throws IOException {
        articleService.setArticle(article);
        Router.Instance().changeView(Views.Preview);
    }
}
