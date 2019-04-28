package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Controller.Router.*;
import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.TwitterService;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
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
    private ChangeListener listener;



    private enum DisplayMode {

        All("All"),
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


        //remove previews listner
        if (listener != null) {
            listView.getSelectionModel().selectedItemProperty().removeListener(listener);
        }

        listener = (ov, oldValue, newValue) -> {
            int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedArticleIndex == -1) return; // if no item in the listView is selected. Javafx unselect the item when it loses screen focus
            Article article = articles.get(selectedArticleIndex);
            FeedController.this.displayArticlePreview(article);
        };

        listView.getSelectionModel().selectedItemProperty().addListener(listener);

        showCell(thumbnailList);


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

        String displayMode = displayModeChoiceBox.getSelectionModel().getSelectedItem().toString();

        if (displayMode.equals(DisplayMode.All.mode)){ // Mean that he want to see everything
            ArrayList<Article> twitterArticles = twitterService.getStatusAll();
            ArrayList<Article> rssArticles = articleService.getArticleAll();
            rssArticles.addAll(twitterArticles);
            fillListViewWith(rssArticles); }
        else if (displayMode.equals(DisplayMode.Twitter.mode)) { //only twitter feed

            fillListViewWith(twitterService.getStatusAll()); }

        else if (displayMode.equals(DisplayMode.Rss.mode)) { // only rss feed
            fillListViewWith(articleService.getArticleAll()); }

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
                        DisplayMode.Facebook.mode,
                        DisplayMode.All.mode
                );


        displayModeChoiceBox.setItems(categoryList);
        displayModeChoiceBox.setValue(DisplayMode.Rss.mode);

        displayArticles();

    }

    @Override
    public void update(Observable o, Object arg) {
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
