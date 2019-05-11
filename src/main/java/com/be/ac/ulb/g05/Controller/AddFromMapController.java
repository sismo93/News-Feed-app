package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.*;
import net.java.html.leaflet.Map;
import net.java.html.leaflet.event.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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

    public AnchorPane mapcontainer;


    /**
     * List With All available Article on the Map
     */
    private ArrayList<Article> availableArticle;



    /**
     * Website object
     */
    private Article currentArticle;

    /**
     * Website parser
     */
    private  ParserWebSite parserWebsite;

    /**
     * WebView
     */
    private WebView webView;

    /**
     * Map
     */
    private Map map;

    /**
     * CurrentWebSite
     * WebSite of the chosen article
     */
    private Website currentWebSite;



    /**
     * Will Allow us to find each article from the 5 website
     * These articles will be display on the right city
     */
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
        return isExist;
    }

    /**
     * @param availableArticleEachCity
     * @param web
     * Will allow us to display article on the map
     * Each article will have the form of a red cercle
     */
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
                    currentArticle = article;
                    currentWebSite = web;
                    try {
                        selectedArticleImport();
                    } catch (IOException e) {
                        showAlert("We could not import this article, try later","Information");
                    }
                }));
            }
        }));

    }

    public void selectedArticleImport( ) throws IOException {
        Article article = currentArticle;
        article.setContent(parserWebsite.ParserArticle(article.getLink())); // Call the parser

        article.setImage(parserWebsite.ParserImage(article.getLink())); //Add Picture

        article.setDefaultThumbnail(currentWebSite.getDefaultThumbnail()); // add thumbnail
        article.setSource(currentWebSite.getSourceArticle()); // set the Source
        article.setGeolocation(currentWebSite.getGeolocation());
        article.setVideo(parserWebsite.ParserVideo(article.getLink()));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Import");
        alert.setHeaderText(article.getTitle());
        alert.setContentText(article.getDescription());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            articleService.addArticle(article);
            showAlert("Article Added","Information");


        }


    }






    /**
     * Called after scene loading
     * <p>
     * Initialize the various controls on the screen
     */

    @Override
    public void setupView() {
        availableArticle = new ArrayList<>();
        parserWebsite = new ParserWebSite();

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


        URL url = this.getClass().getResource("/Pages/index.html");


        FXBrowsers.load(webView, url, () -> {

            map = new Map("map");

            // from here we just use the Leaflet API to show some stuff on the map
            map.setView(new LatLng(51.505, -0.09), 7);
            map.addLayer(new TileLayer("https://tile.thunderforest.com/transport/{z}/{x}/{y}.png",
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



}
