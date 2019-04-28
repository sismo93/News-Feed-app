package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Controller.Router.*;
import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.TwitterService;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static com.be.ac.ulb.g05.Controller.ArticleViewController.saveImage;

/**
 * AbstractController of the ArticleService View
 *
 * @author @MnrBn
 * @codereview @borsalinoK
 */
public class FeedController extends AbstractController {


    /**
     * UI element
     */
    public VBox container;
    public ChoiceBox displayModeChoiceBox;
    public ListView listView;

    /**
     * Services in use
     */
    private TwitterService twitterService;
    private ChangeListener listener;


    /**
     * @param cell
     * @return a listView
     * Allow us to click on cell + show the right information
     */
    private ListView<PreviewThumbnailCell> showCell(ObservableList<PreviewThumbnailCell> cell) {

        listView.setItems(cell);
        int cellsize = 150;
        listView.setFixedCellSize(cellsize);

        listView.setCellFactory(new Callback<ListView<PreviewThumbnailCell>, ListCell<PreviewThumbnailCell>>() {
            @Override
            public ListCell<PreviewThumbnailCell> call(ListView<PreviewThumbnailCell> param) {
                return new ListCell<PreviewThumbnailCell>() {

                    @Override
                    protected void updateItem(PreviewThumbnailCell pr, boolean empty) {
                        super.updateItem(pr, empty);

                        if (pr != null) {
                            setGraphic(pr.getImage());
                            setText(pr.getTitle() + "\n\n" + "Date : " + pr.getDate() +
                                    "\n" + "Source : " + pr.getSource() + "\n" + "Localisation : " +
                                    pr.getLocalisation());

                        }
                    }
                };
            }
        });

        return listView;
    }

    /**
     * @param articles list of articles
     *                 Function that allow us to display the picture + the information about the article
     */
    private void fillListViewWith(ArrayList<Article> articles) {

        ObservableList<PreviewThumbnailCell> thumbnailList = FXCollections.observableArrayList();

        articles.forEach(article -> {
            ImageView imageView = new ImageView();
            try {
                saveImage(article.getDefaultThumbnail(), "file:temp-feedbuzz-v.jpg", imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PreviewThumbnailCell previewThumbnail = new PreviewThumbnailCell(imageView, article.getTitle(),
                    article.getPubDate(), article.getGeolocation(), article.getSource());
            thumbnailList.add(previewThumbnail);
        });


        Platform.runLater(() -> {
            //remove previews listner
            if (listener != null) {
                listView.getSelectionModel().selectedItemProperty().removeListener(listener);
            }

            listener = (ov, oldValue, newValue) -> {
                int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
                if (selectedArticleIndex == -1)
                    return; // if no item in the listView is selected. Javafx unselect the item when it loses screen focus
                Article article = articles.get(selectedArticleIndex);
                FeedController.this.displayArticlePreview(article);
            };

            listView.getSelectionModel().selectedItemProperty().addListener(listener);

            showCell(thumbnailList);

        });


    }

    /**
     * refresh the view
     */
    public void refreshContainer() {
        displayArticles();
    }


    /**
     * Display the right article type of article on the feed (tweet/article/both)
     */
    private void displayArticles() {

        addTagToChoiceBox();

        String displayMode = displayModeChoiceBox.getSelectionModel().getSelectedItem().toString();

        ArrayList<Article> articleList = sortByTag(displayMode);
        fillListViewWith(articleList);
    }


    /**
     * @param tag
     * @return the right list of article
     * <p>
     * Allow us to only keep on the feed articles that match the tag
     */
    private ArrayList<Article> sortByTag(String tag) {
        ArrayList<Article> twitterArticles = twitterService.getTwitterArticleObj();
        ArrayList<Article> allArticle = articleService.getArticleAll();
        allArticle.addAll(twitterArticles); // we have now all article but we need to sort by tag
        ArrayList<Article> articlesToShow = new ArrayList<>();
        for (Article article : allArticle) {
            for (String differentTag : article.getTags()) {
                if (differentTag.equals(tag)) {
                    articlesToShow.add(article);
                }
            }
        }
        return articlesToShow;
    }


    /**
     * check if we need to add the tag to the choicebox
     * if so, add him
     */
    private void addTagToChoiceBox() {
        List<String> tagList = twitterService.getTagList();
        boolean hasToAdd = true;
        for (String tag : tagList) {
            for (int choice = 0; choice < displayModeChoiceBox.getItems().size(); choice++) {
                if (tag.equals(displayModeChoiceBox.getItems().get(choice))) {
                    hasToAdd = false;
                }
            }
            if (hasToAdd) {
                displayModeChoiceBox.getItems().add(tag);
            }
            hasToAdd = true;

        }
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

        ObservableList<String> categoryList =
                FXCollections.observableArrayList(
                        "All",
                        "Rss",
                        "Twitter",
                        "Facebook"
                );


        displayModeChoiceBox.setItems(categoryList);
        displayModeChoiceBox.setValue("All");

        displayArticles();

    }

    /**
     * Displays article preview
     *
     * @param article Article object
     */
    public void displayArticlePreview(Article article) {
        articleService.selectArticle(article);
        Router.Instance().changeView(Views.Preview);
    }


    /**
     * @param dependencyInjector object responsible for delivering the dependency
     *                           called on initialization
     *                           link the client to his specific services
     */
    @Override
    public void injectDependencies(DependencyInjector dependencyInjector) {
        super.injectDependencies(dependencyInjector);
        twitterService = dependencyInjector.getTwitterService();
    }

    @Override
    public void onActive() {
        displayArticles();
    }
}
