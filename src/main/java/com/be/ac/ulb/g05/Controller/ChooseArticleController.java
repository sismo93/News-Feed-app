package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import static com.be.ac.ulb.g05.Controller.AddFromWebSiteController.*;


/**
 * @author @Mnrbn
 * @codereview @mouscb
 * Allow us to handle all the article that the user want to add for the feed
 * It will be a list of different article, each time an article is added, it will be removed from
 * the view.
 */
public class ChooseArticleController extends AbstractController implements Observer {


    /**
     * Container for the Article
     */
    public VBox articleContainer;


    /**
     * Chosen article by user
     */
    private Article articleChosen;


    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    @Override
    public void setupView(){
        allAvailableArticle.addObserver(this); // will call update when AllAvailableArticle change
        articleService.addObserver(this);
        pushAvailableArticlesToView();

    }

    /**
     * Updates the article feed
     * @param o observable
     * @param arg object argument
     */
    @Override
    public void update(Observable o, Object arg) {
        deleteChosenArticle();
        pushAvailableArticlesToView();
    }


    /**
     * Sets the article service
     * @param articleService article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

    /**
     * Remove the chosen article from all the available article
     */
    private void deleteChosenArticle() {
        availableArticleStatic.remove(articleChosen);
    }


    /**
     * Show Title + preview + button to let the user choose if he want to take the
     * article or not
     */
    private void pushAvailableArticlesToView(){

        articleContainer.getChildren().clear();
        ObservableList<PreviewThumbnailCell> previewArticlesList = FXCollections.observableArrayList();
        availableArticleStatic.forEach(article -> {
            Button button = new Button("Add article");
            button.setPrefSize(100,100);
            PreviewThumbnailCell previewArticleCell = new PreviewThumbnailCell(article.getTitle(),article.getDescription(),button,article);
            previewArticlesList.add(previewArticleCell);
        });

       showCell(previewArticlesList);
    }


    /**
     * @param cell
     * Allow us to click on cell. Same code used on feedController
     * better duplicate than putting a static function
     */
    private void showCell (ObservableList<PreviewThumbnailCell> cell){
        ListView<PreviewThumbnailCell> listView = new ListView<>(cell);
        listView.setFixedCellSize(100);

        listView.setCellFactory(new Callback<ListView<PreviewThumbnailCell>, ListCell<PreviewThumbnailCell>>() {
            @Override
            public ListCell<PreviewThumbnailCell> call(ListView<PreviewThumbnailCell> param) {
                return new ListCell<PreviewThumbnailCell>() {

                    @Override
                    protected void updateItem(PreviewThumbnailCell pr, boolean empty) {
                        super.updateItem(pr, empty);

                        if (pr != null) {

                            setGraphic(pr.getButton());

                            setText(pr.getTitle() + "\n\n" +"Preview : "+pr.getPreviewText());
                            pr.getButton().setOnAction(event -> {
                                try {
                                    articleChosen = pr.getArticle();
                                    addArticleForFeed(articleChosen);


                                } catch (IOException e) {
                                    AddFromMapController.showAlert("An Error occurred with your choice of Article","Error");
                                }
                            });

                        }
                    }
                };
            }
        });
        articleContainer.getChildren().add(listView);

    }

    /**
     * Add the article that the user choose to the List
     * Set different information about the added article
     */
    private void addArticleForFeed(Article article) throws IOException {
        Article fixedArticle = fixChangeForArticle(article,parserWebsite,website);
        articleService.addArticle(fixedArticle);
        AddFromMapController.showAlert("Article Added","Information");
    }
}
