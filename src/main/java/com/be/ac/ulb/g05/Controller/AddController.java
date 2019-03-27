package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


/**
 * Controller of the Add View
 * @author @MnrBn
 * @codereview @Mouscb
 */
public class AddController extends Controller {

    /**
     * Controls elements displayed on screen
     */
    @FXML
    public ComboBox ArticleNumberBox;
    @FXML
    public ComboBox CategoryBox;
    @FXML
    public Button AddButton;
    @FXML
    public ComboBox SourceArticleBox;

    /**
     * Website object
     */
    private Website website;

    /**
     * Holds a list of current articles
     */
    private ArrayList<Article> CurrentArticleList;

    /**
     * Website parser
     */
    private ParserWebSite parserWebsite;


    /**
     * @param source is the website that the user want article from
     * @return an Website object
     * Create the right Object for the right website
     */
    public Website CreateObjectSource(String source){
        switch (source) {
            case "TheGuardian.co":
                return website = new TheGuardian();
            case "Lepoint.fr":
                return website = new LePoint();
            case "RTLinfo.be":
                return website = new RTL();
            case "LeMonde.fr":
                return website = new LeMonde();
            default:
                return website = new LeFigaro();
        }
    }

    /**
     * Add Article to the list
     * Take the same number of article that the user want
     */
    public void AddCurrentArticleList(){
        RSSFeedParser parser = new RSSFeedParser(website.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));

        ArrayList<Article> articles = parser.readRSS();

        for (int i=0;i<(int) ArticleNumberBox.getSelectionModel().getSelectedItem();i++){ // add Article to the list
            CurrentArticleList.add(articles.get(i));
        }
    }


    /**
     * @param actionEvent
     * Add the article to the feed
     */
    public void OnButtonPressed(ActionEvent actionEvent) throws IOException {
        if(CategoryBox.getSelectionModel().isEmpty() ||
                ArticleNumberBox.getSelectionModel().isEmpty()
                || SourceArticleBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have to choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        CreateObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!website.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This category doesn't exist on this website", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        AddCurrentArticleList(); // Add Article to the list
        ShowArticleFound();

    }

    /**
     * @param article the article source
     * @param articleObject the article object
     * change the description if its TheGuardian or RTLINFO
     * because these 2 websites doesnt handle the description for an article
     */
    public void FixDescriptionError(String article, Article articleObject){
        String source = (String) SourceArticleBox.getSelectionModel().getSelectedItem();

        if ((source.equals("TheGuardian.co")) || (source.equals("RTLinfo.be"))) {
            articleObject.setDescription(article.substring(0,100));
        }
    }


    /**
     * @throws IOException
     * Shows the articles found + title + description
     * The user can accept them or denied them
     */
    public void ShowArticleFound() throws IOException {
        for (Article currentArticle : CurrentArticleList) {
            currentArticle.setContent(parserWebsite.ParserArticle(currentArticle.getLink())); // Call the parser

            FixDescriptionError(currentArticle.getContent(), currentArticle); // Change the description

            //Popup dialog window
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    currentArticle.getDescription() + " ...",
                    ButtonType.OK, ButtonType.CANCEL);
            //Setup dialog window controls
            ButtonType importButton = new ButtonType("Import");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(importButton, cancelButton);
            alert.setTitle("Article Preview ");
            alert.setHeaderText(currentArticle.getTitle());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == importButton) {
                // ... user chose OK so we add the articleObject to the List
                currentArticle.setImage(parserWebsite.ParserImage(currentArticle.getLink())); //Add Picture
                currentArticle.setDefaultThumbnail(website.getDefaultThumbnail()); // add thumbnail
                currentArticle.setSource(website.getSourceArticle()); // set the Source
                currentArticle.setGeolocation(website.getGeolocation());

                articleService.addArticle(currentArticle);
            }
        }
        CurrentArticleList.clear();

    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

    /**
     * Called after scene loading
     *
     * Initialize the various controls on the screen
     */

    @Override
    public void setupView() {
        CurrentArticleList = new ArrayList<>();
        parserWebsite = new ParserWebSite();

        ObservableList<String> sourceArticle =
                FXCollections.observableArrayList(
                        "TheGuardian.co",
                        "Lepoint.fr",
                        "LeFigaro.fr",
                        "RTLinfo.be",
                        "LeMonde.fr"
                );
        ObservableList<String> categoryList =
                FXCollections.observableArrayList(
                        "Actualite",
                        "Politique",
                        "Environnement",
                        "Belgique",
                        "International",
                        "Culture",
                        "Sante",
                        "Economie",
                        "Sport",
                        "Technologies"
                );


        ObservableList<Integer> articleNumberList =
                FXCollections.observableArrayList(
                        1,
                        2,
                        3
                );

        ArticleNumberBox.setItems(articleNumberList);

        CategoryBox.setItems(categoryList);

        SourceArticleBox.setItems(sourceArticle);

    }
}
