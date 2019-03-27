package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
    @FXML
    public StackPane stackPane;

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
            showAlert("A selection is empty, please fill it", 0, null);
            return;
        }

        CreateObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!website.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())){
            showAlert("This category does not exist on this website. Please choose another.", 0, null);
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
            showAlert(currentArticle.getDescription(), 1, currentArticle);
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

    public void showAlert(String message, int flag, Article article) {

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Information"));
        content.setBody(new Text(message));
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        if (flag == 0) {
            JFXButton button = new JFXButton("Ok");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });

            content.setActions(button);
            dialog.show();
        } else if (flag == 1) {
            JFXButton importButton = new JFXButton("Import");
            JFXButton cancelButton = new JFXButton("Cancel");

            importButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        importWebsite(article);
                        dialog.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dialog.close();
                }
            });
            content.setActions(importButton, cancelButton);
            dialog.show();
        }
    }

    public void importWebsite(Article article) throws IOException {
        article.setImage(parserWebsite.ParserImage(article.getLink())); //Add Picture
        article.setDefaultThumbnail(website.getDefaultThumbnail()); // add thumbnail
        article.setSource(website.getSourceArticle()); // set the Source
        article.setGeolocation(website.getGeolocation());

        articleService.addArticle(article);
    }
}
