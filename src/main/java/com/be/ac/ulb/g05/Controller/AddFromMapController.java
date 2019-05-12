package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
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
    public ComboBox<String> CategoryBox;

    @FXML
    public StackPane stackPane;

    public AnchorPane mapContainer;


    /**
     * List With All available Article on the Map
     */
    private ArrayList<Article> availableArticle;


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
     * Will Allow us to find each article from the 5 website
     * These articles will be display on the right city
     */
    public void OnAddMapButtonPressed( ) {

        if ( CategoryBox.getSelectionModel().isEmpty()) {
            showAlert("A selection is empty, please fill it", "Information");
            return;
        }
        List<String> diffSource = Arrays.asList("TheGuardian","Lepoint","LeMonde","LeFigaro","RTLinfo");
        String category = CategoryBox.getSelectionModel().getSelectedItem();


        for (String source: diffSource) {
            Website web= CreateObjectSource(source);
            if (web.isCategoryExist(category)) { // check if the category exist
                RSSFeedParser parser = new RSSFeedParser(web.getLink(category));
                ArrayList<Article> availableArticleEachCity = parser.readRSS();
                displayArticleOnMap(availableArticleEachCity,web);
            }
        }



    }

    /**
     * @param article article that the user choose
     * @return boolean
     * Function that will check if we already have the article on the list
     */
    private boolean isArticleAlreadyOnTheMap(Article article){
        boolean isExist = false;
        for (Article availableArt: availableArticle)
            if (availableArt.getTitle().equals(article.getTitle())){
                isExist = true;
            }
        return isExist;
    }


    /**
     * @param availableArticleEachCity list of available article specific to a city
     * @param web object WebSite relative to the right journal
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
                    try {
                        selectedArticleImport(web,article);
                    } catch (IOException e) {
                        showAlert("We could not import this article, try later","Information");
                    }
                }));
            }
        }));

    }

    /**
     * @param web object WebSite relative to the right journal
     * @param currentArticle the choosed article
     * @throws IOException
     * Show a popup to the user and he chose whether he want to add or not
     */
    private void selectedArticleImport(Website web,Article currentArticle) throws IOException {
        Article article = fixChangeForArticle(currentArticle,parserWebsite,web);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Import");
        alert.setHeaderText("Do you want to import this article ?");
        alert.setContentText(article.getTitle());

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


        CategoryBox.setItems(getCategoryList());

        displayMap();

    }


    private void displayMap() {

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

        mapContainer.getChildren().add(webView);



    }



}
