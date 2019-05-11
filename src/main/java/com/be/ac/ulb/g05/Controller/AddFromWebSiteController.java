package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
import com.be.ac.ulb.g05.Controller.Router.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

import static com.be.ac.ulb.g05.Controller.AddFromMapController.showAlert;
import static com.be.ac.ulb.g05.Controller.AddController.WebsiteCategory.*;
import static com.be.ac.ulb.g05.Controller.AddController.WebsiteSource.*;


/**
 * AbstractController of the Add View
 *
 * @author @MnrBn
 * @codereview @Mouscb
 */
public class AddFromWebSiteController extends AddController {

    /**
     * Controls elements displayed on screen
     */
    @FXML
    public ComboBox CategoryBox;
    @FXML
    public Button AddButton;
    @FXML
    public ComboBox SourceArticleBox;
    @FXML
    public StackPane stackPane;

    public static ArrayList<Article> availableArticleStatic;


    public static ArticleService allAvailableArticle;


    /**
     * Website object
     */
    private static Website website;



    /**
     * Website parser
     */
    private static ParserWebSite parserWebsite;


    /**
     * Take all article available
     *
     */
    private void AddAllArticleAvailable() {
        RSSFeedParser parser = new RSSFeedParser(website.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));

        availableArticleStatic = parser.readRSS();



    }

    public static ParserWebSite getParserWebsite(){
        return parserWebsite;
    }

    public static Website getWebsite(){
        return website;
    }

    /**
     * Function that will allow us to have an ArticleService object with all the available article
     * needed in order to use Observable (call update when the object change)
     */
    private void changeToArticleService(){
        availableArticleStatic.forEach(article -> {
            allAvailableArticle.addArticle(article);
        });
    }



    /**
     * Handler when Add button is pressed
     */
    public void OnButtonPressed()  {
        if (CategoryBox.getSelectionModel().isEmpty()
                || SourceArticleBox.getSelectionModel().isEmpty()) {
            showAlert("A selection is empty, please fill it", "Information");
            return;
        }

        website = CreateObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!website.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())) {
            showAlert("This category does not exist on this website. Please choose another.", "Information");
            return;
        }

        AddAllArticleAvailable();
        changeToArticleService();
        Router.Instance().changeView(Views.ChooseArticle); //Open The view ChooseArticleView
    }




    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

    /**
     * Called after scene loading
     * <p>
     * Initialize the various controls on the screen
     */

    @Override
    public void setupView() {
        availableArticleStatic = new ArrayList<>();
        parserWebsite = new ParserWebSite();
        allAvailableArticle = new ArticleService();

        ObservableList<String> sourceArticle =
                FXCollections.observableArrayList(
                        Guardian.source,
                        LePoint.source,
                        LeFigaro.source,
                        RTL.source,
                        LeMonde.source
                );
        ObservableList<String> categoryList =
                FXCollections.observableArrayList(
                        Actualite.category,
                        Politique.category,
                        Environnement.category,
                        Belgique.category,
                        International.category,
                        Culture.category,
                        Sante.category,
                        Economie.category,
                        Sport.category,
                        Technologies.category
                );

        CategoryBox.setItems(categoryList);

        SourceArticleBox.setItems(sourceArticle);

    }



}
