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
    public ComboBox<String> CategoryBox;
    @FXML
    public Button AddButton;
    @FXML
    public ComboBox<String> SourceArticleBox;
    @FXML
    public StackPane stackPane;


    /**
     * Website object
     * Will be used in chooseArticleController
     */
    protected static Website website;



    /**
     * Website parser
     * Will be used in chooseArticleController
     */
    protected static ParserWebSite parserWebsite;



    /**
     * List of All available article
     * Will be used in chooseArticleController
     */
    protected static ArrayList<Article> availableArticleStatic;


    /**
     * All availableArticle but ArticleService object
     * when this variable change, the view on ChooseArticleController will be update
     * (design pattern observer)
     */
    protected static ArticleService allAvailableArticle;



    /**
     * Take all article available
     *
     */
    private void addAllArticleAvailable() {
        RSSFeedParser parser = new RSSFeedParser(website.getLink(CategoryBox.getSelectionModel().getSelectedItem()));

        availableArticleStatic = parser.readRSS();



    }

    /**
     * Function that will allow us to have an ArticleService object with all the available article
     * needed in order to use Observable (call update when the object change)
     */
    private void changeToArticleService(){
        availableArticleStatic.forEach(article -> allAvailableArticle.addArticle(article));
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

        website = CreateObjectSource(SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!website.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem())) {
            showAlert("This category does not exist on this website. Please choose another.", "Information");
            return;
        }

        addAllArticleAvailable();
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

        CategoryBox.setItems(getCategoryList());

        SourceArticleBox.setItems(sourceArticle);

    }



}
