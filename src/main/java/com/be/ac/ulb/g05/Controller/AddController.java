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
    public ChoiceBox ArticleNumberBox;
    @FXML
    public ChoiceBox CategoryBox;
    @FXML
    public Button AddButton;
    @FXML
    public ChoiceBox SourceArticleBox;

    private WebSite webSite;

    private ArrayList<Article> CurrentArticleList;
    private ParserWebSite parserWebsite;




    public WebSite CreateObjectSource(String source){
        if (source == "TheGuardian.co"){
            return webSite = new TheGuardian();
        }
        else if (source == "Lepoint.fr"){
            return webSite = new LePoint();
        }
        else if (source == "RTLinfo.be"){
            return webSite = new RTL();
        }
        else if (source == "LeMonde.fr"){
            return webSite = new LeMonde();
        }
        else{
            return webSite = new LeFigaro();
        }
    }

    /**
     * Add Article to the list
     * Take the same number of article that the user want
     */
    public void AddCurrentArticleList(){

        RSSFeedParser parser = new RSSFeedParser(webSite.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));

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

        if (!webSite.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This category doesnt exist on this website", ButtonType.OK);
            alert.showAndWait();
            return;
        }


        AddCurrentArticleList(); // Add Article to the list
        ShowArticleFound();

    }

    /**
     * @param article
     * @param articleObject
     * change the description if its TheGuardian or RTLINFO
     * because these 2 websites doesnt handle the description for an article
     */
    public void FixDescriptionError(String article,Article articleObject){
        String source =(String) SourceArticleBox.getSelectionModel().getSelectedItem();
        if ((source == "TheGuardian.co") || (source =="RTLinfo.be")) {
            articleObject.setDescription(article.substring(0,100));
        }
    }


    /**
     * @throws IOException
     * Shows the articles found + title + description
     * The user can accept them or denied them
     */
    public void ShowArticleFound() throws IOException {
        for (int i = 0; i < CurrentArticleList.size(); i++) {
            Article currentArticle = CurrentArticleList.get(i);
            currentArticle.setArticle(parserWebsite.ParserArticle(currentArticle.getLink())); // Call the parser


            FixDescriptionError(currentArticle.getArticle(),currentArticle); // Change the description

            //Popup dialog window
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    currentArticle.getDescription()+ " ...",
                    ButtonType.OK, ButtonType.CANCEL);
            //Setup dialog window controls
            ButtonType importButton = new ButtonType("Import");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(importButton, cancelButton);
            alert.setTitle("Article Preview ");
            alert.setHeaderText(currentArticle.getTitle());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == importButton){
                // ... user chose OK so we add the articleObject to the List
                currentArticle.setImage(parserWebsite.ParserImage(currentArticle.getLink())); //Add Picture
                currentArticle.setDefaultThumbnail(webSite.getDefaultThumbnail()); // add thumb
                currentArticle.setSource(webSite.getSourceArticle()); // set the Source
                currentArticle.setGeolocation(webSite.getGeolocation());


                articleService.addArticle(currentArticle);
            }
        }
        CurrentArticleList.clear();

    }

    /**
     * @param actionEvent
     * Open the Menu View
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        RootController.Instance().changeView(RootController.Views.Menu);
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
