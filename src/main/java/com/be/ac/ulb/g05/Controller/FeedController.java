package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Controller.Router.*;
import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.TwitterService;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import twitter4j.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static com.be.ac.ulb.g05.Controller.ArticleViewController.saveImage;

/**
 * AbstractController of the ArticleService View
 *
 * @author @MnrBn
 * @codereview @borsalinoK
 */
public class FeedController extends AbstractController implements Observer {

    /**
     * ArticleViewField displays the content on the page
     */
    public VBox container;
    public ChoiceBox displayModeChoiceBox;
    public ListView listView;

    private TwitterService twitterService;
    private int cellsize = 150;
    ChangeListener listener;

    private enum DisplayMode {

        Rss("Rss"),
        Facebook("facebook"),
        Twitter("twitter");

        private String mode;

        DisplayMode(String m) {
            this.mode = m;
        }
    }

    /**
     * @param cell
     * @return a listView
     * Allow us to click on cell + show the right information
     */
    private ListView<PreviewThumbnailCell> showCell(ObservableList<PreviewThumbnailCell> cell) {

        listView.setItems(cell);
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

        showCell(thumbnailList);

        //remove previews listner
        if(listener != null) {
            listView.getSelectionModel().selectedItemProperty().removeListener(listener);
        }

        listener = (ov, oldValue, newValue) -> {

            int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
            Article article = articles.get(selectedArticleIndex);
            System.out.println(selectedArticleIndex + " " + articles.size());
            try {
                FeedController.this.displayArticlePreview(article);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void refreshContainer(ActionEvent actionEvent) {
        displayArticles();
    }

    private void displayArticles() {

        String displayMode = displayModeChoiceBox.getSelectionModel().getSelectedItem().toString();
        System.out.println("display mode " + displayMode);
        if (displayMode.equals(DisplayMode.Twitter.mode)) {
            fillListViewWith(twitterService.getStatusAll());
        } else if (displayMode.equals(DisplayMode.Rss.mode)) {

            ArrayList<Article> twitterArticles = twitterService.getStatusAll();
            ArrayList<Article> rssArticles = articleService.getArticleAll();
            rssArticles.addAll(twitterArticles);
            fillListViewWith(rssArticles);
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
        articleService.addObserver(this);
        twitterService.addObserver(this);

        ObservableList<String> categoryList =
                FXCollections.observableArrayList(
                        DisplayMode.Rss.mode,
                        DisplayMode.Twitter.mode,
                        DisplayMode.Facebook.mode
                );


        displayModeChoiceBox.setItems(categoryList);
        displayModeChoiceBox.setValue(DisplayMode.Rss.mode);
        displayArticles();

    }


    /**
     * Displays article preview
     *
     * @param article Article object
     * @throws IOException if article cannot be read
     */
    public void displayArticlePreview(Article article) throws IOException {
        articleService.selectArticle(article);
        Router.Instance().changeView(Views.Preview);
    }

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
