package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.ParserWebSite;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
import com.be.ac.ulb.g05.Website;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static com.be.ac.ulb.g05.Controller.AddController.*;


/**
 * @author @Mnrbn
 * @codereview @mouscb
 * Allow us to handle all the article that the use want to add for the feed
 * It will be a list of different article, each time an article is added, it will be removed from
 * the available article.
 */
public class ChooseArticleController extends AbstractController implements Observer {


    public VBox articleContainer;


    private Website website;
    private ParserWebSite parserWebsite;

    private ArrayList<Article> availableArticle;

    private Article articleChosed;





    @Override
    public void setupView(){


        getAllObjectFromAddController();
        allAvailableArticle.addObserver(this); // will call update when AllAvailableArticle change
        articleService.addObserver(this);
        pushAvailableArticlesToView();

    }

    @Override
    public void update(Observable o, Object arg) {
        getAllObjectFromAddController();
        deleteChosenArticle();
        pushAvailableArticlesToView();
    }

    /**
     * Remove the chosen article from all the available article
     */
    private void deleteChosenArticle() {
        availableArticle.remove(articleChosed);
    }


    /**
     * Get all object that will be needed for the controller
     */
    private void getAllObjectFromAddController(){
        ArrayList<Article> allArticleAvailable = getAvailableArticle();
        setAvailableArticle(allArticleAvailable);

        Website website = getWebsite();

        setWebsite(website);

        ParserWebSite parserWebSite = getParserWebsite();
        setParserWebsite(parserWebSite);
    }

    /**
     * Show Title + preview + button to let the user choose if he want to take the
     * article or not
     */
    private void pushAvailableArticlesToView(){

        articleContainer.getChildren().clear();
        ObservableList<PreviewThumbnailCell> previewArticlesList = FXCollections.observableArrayList();
        availableArticle.forEach(article -> {
            Button button = new Button("Add article");
            button.setPrefSize(100,100);
            PreviewThumbnailCell previewArticleCell = new PreviewThumbnailCell(article.getTitle(),article.getDescription(),button,article);
            previewArticlesList.add(previewArticleCell);
        });

       showCell(previewArticlesList); //call


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
                                    articleChosed = pr.getArticle();
                                    AddArticleForFeed(articleChosed);


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                        }
                    }
                };
            }
        });
        this.articleContainer.getChildren().add(listView);

    }




    /**
     * Add the article that the use choose to the List
     * Set different information about the added article
     */
    private void AddArticleForFeed(Article article) throws IOException {
        article.setContent(parserWebsite.ParserArticle(article.getLink())); // Call the parser


        article.setImage(parserWebsite.ParserImage(article.getLink())); //Add Picture
        article.setDefaultThumbnail(website.getDefaultThumbnail()); // add thumbnail
        article.setSource(website.getSourceArticle()); // set the Source
        article.setGeolocation(website.getGeolocation());
        article.setVideo(parserWebsite.ParserVideo(article.getLink()));

        articleService.addArticle(article);
        showAlert("Article Added","Information");
    }


    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }


    private void setWebsite(Website website) {
        this.website = website;
    }

    private void setParserWebsite(ParserWebSite parserWebsite) {
        this.parserWebsite = parserWebsite;
    }

    public void setAvailableArticle(ArrayList<Article> availableArticle) {
        this.availableArticle = availableArticle;
    }


}
