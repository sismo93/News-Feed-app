package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.*;
import net.java.html.leaflet.event.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.be.ac.ulb.g05.Controller.AddController.WebsiteCategory.*;


/**
 * AbstractController of the Add View
 *
 * @author @MnrBn
 * @codereview @Mouscb
 */
public class AddFromMapController extends AddController {

    /**
     * Controls elements displayed on screen
     */
    @FXML
    public ComboBox CategoryBox;

    @FXML
    public StackPane stackPane;

    public static ArrayList<Article> availableArticle;


    public static ArticleService allAvailableArticle;
    public Label selectedArticle;



    /**
     * Website object
     */
    private Article currentArticle;

    /**
     * Website parser
     */
    private static ParserWebSite parserWebsite;
    public AnchorPane mapcontainer;
    private WebView webView;
    private Map map;
    private Website currentWebSite;



    public void OnAddMapButtonPressed( ) {

        if ( CategoryBox.getSelectionModel().isEmpty()) {
            showAlert("A selection is empty, please fill it", "Information");
            return;
        }
        List<String> diffSource = Arrays.asList("TheGuardian","Lepoint","LeMonde","LeFigaro","RTLinfo");
        String category = (String) CategoryBox.getSelectionModel().getSelectedItem();


        for (String source: diffSource) {
            Website web= CreateObjectSource(source);
            if (web.isCategoryExist(category)) {
                RSSFeedParser parser = new RSSFeedParser(web.getLink(category));
                ArrayList<Article> availableArticleEachCity = parser.readRSS();

                displayArticleOnMap(availableArticleEachCity,web);
            }
        }



    }

    /**
     * @param article
     * @return boolean
     * Function that will check if we already have the article on the list
     */
    private boolean isArticleAlreadyOnTheMap(Article article){
        Boolean isExist = false;
        for (Article availableArt: availableArticle)
            if (availableArt.getTitle().equals(article.getTitle())){
                isExist = true;
            }
        ;
        return isExist;
    }

    private void displayArticleOnMap(ArrayList<Article> availableArticleEachCity, Website web) {

        Random random = new Random();
        FXBrowsers.runInBrowser(webView, () -> availableArticleEachCity.forEach(article -> {
            if (!isArticleAlreadyOnTheMap(article)) {
                availableArticle.add(article);

                double rdDoubleLat = (random.nextDouble() - .5) * .5;
                double rdDoubleLon = (random.nextDouble() - .5) * .5;
                map.addLayer(new Circle(new LatLng(web.getLatitude() + rdDoubleLat, web.getLongitude() + rdDoubleLon), 500,
                        new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5)
                ).bindPopup(article.getTitle()).addMouseListener(MouseEvent.Type.CLICK, mouseEvent -> {
                    selectedArticle.setText(article.getTitle());
                    currentArticle = article;
                    currentWebSite = web;
                }));
            }
        }));

    }

    public void selectedArticleImport( ) {
        Article article = currentArticle;
        try {
            article.setContent(parserWebsite.ParserArticle(article.getLink())); // Call the parser

            article.setImage(parserWebsite.ParserImage(article.getLink())); //Add Picture

            article.setDefaultThumbnail(currentWebSite.getDefaultThumbnail()); // add thumbnail
            article.setSource(currentWebSite.getSourceArticle()); // set the Source
            article.setGeolocation(currentWebSite.getGeolocation());
            article.setVideo(parserWebsite.ParserVideo(article.getLink()));

            articleService.addArticle(article);
            showAlert("Article Added","Information");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    /**
     * @param source is the website that the user want article from
     *               Create the right Object for the right website
     */



    /**
     * Called after scene loading
     * <p>
     * Initialize the various controls on the screen
     */

    @Override
    public void setupView() {
        availableArticle = new ArrayList<>();
        parserWebsite = new ParserWebSite();
        allAvailableArticle = new ArticleService();

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

        displayMap();

    }


    void displayMap() {

        // we define a regular JavaFX WebView that DukeScript can use for rendering

        webView = new WebView();

        WebEngine webEngine = webView.getEngine();
        URL url = this.getClass().getResource("/Pages/index.html");


        FXBrowsers.load(webView, url, () -> {

            map = new Map("map");

            // from here we just use the Leaflet API to show some stuff on the map
            map.setView(new LatLng(51.505, -0.09), 7);
            map.addLayer(new TileLayer("http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png",
                    new TileLayerOptions()
                            .setAttribution(
                                    "Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, "
                                            + "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
                                            + "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>")
                            .setMaxZoom(18)
                            .setId("eppleton.ia9c2p12")
            ));



        });


        mapcontainer.getChildren().add(webView);



    }

    /**
     * Shows an alert with a custom title & message
     * @param message Body message
     * @param title Header message
     */
    public static void showAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }


}
