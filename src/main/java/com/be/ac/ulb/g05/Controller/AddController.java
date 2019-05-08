package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.*;
import com.be.ac.ulb.g05.Model.*;
import com.be.ac.ulb.g05.Controller.Router.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.*;
import net.java.html.leaflet.event.LayerEvent;
import net.java.html.leaflet.event.LayerListener;
import net.java.html.leaflet.event.MouseEvent;
import net.java.html.leaflet.event.MouseListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.be.ac.ulb.g05.Controller.AddController.WebsiteCategory.*;
import static com.be.ac.ulb.g05.Controller.AddController.WebsiteSource.*;

/**
 * AbstractController of the Add View
 *
 * @author @MnrBn
 * @codereview @Mouscb
 */
public class AddController extends AbstractController {

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

    public static ArrayList<Article> availableArticle;


    public static ArticleService allAvailableArticle;
    public Label selectedArticle;

    private double latParis = 51.508;
    private double longParis = -0.11;
    /**
     * Website object
     */
    private static Website website;
    private Article currentArticle;

    /**
     * Website parser
     */
    private static ParserWebSite parserWebsite;
    public AnchorPane mapcontainer;
    private WebView webView;
    private Map map;


    public static Website getWebsite() {
        return website;
    }

    public static ParserWebSite getParserWebsite() {
        return parserWebsite;
    }

    public void OnAddButtonPressed(ActionEvent actionEvent) {
        if (CategoryBox.getSelectionModel().isEmpty()
                || SourceArticleBox.getSelectionModel().isEmpty()) {
            showAlert("A selection is empty, please fill it", "Information");
            return;
        }

        CreateObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!website.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())) {
            showAlert("This category does not exist on this website. Please choose another.", "Information");
            return;
        }

        AddAllArticleAvailable();
        changeToArticleService();
        Router.Instance().changeView(Views.ChooseArticle); //Open The view ChooseArticleView
    }

    public void OnAddMapButtonPressed(ActionEvent actionEvent) {

        if ( SourceArticleBox.getSelectionModel().isEmpty()) {
            showAlert("A selection is empty, please fill it", "Information");
            return;
        }

        String source = (String) SourceArticleBox.getSelectionModel().getSelectedItem();
        CreateObjectSource(source);

        RSSFeedParser parser = new RSSFeedParser(website.getLink(Actualite.category));

        availableArticle = parser.readRSS();

        displayArticleOnMap(source);

    }

    private void displayArticleOnMap(String source) {


        Random random = new Random();
        FXBrowsers.runInBrowser(webView, () -> availableArticle.forEach(article -> {
            double rdDoubleLat = (random.nextDouble()- .05)*.1 ;
            double rdDoubleLon = (random.nextDouble()- .05)*.1;
            map.addLayer(new Circle(new LatLng( latParis + rdDoubleLat, longParis + rdDoubleLon), 500,
                    new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5)
            ).bindPopup(article.getTitle()).addMouseListener(MouseEvent.Type.CLICK, new MouseListener() {
                @Override
                public void onEvent(MouseEvent mouseEvent) {
                    selectedArticle.setText(article.getTitle());
                    currentArticle = article;
                }

            }));
        }));

    }

    public void selectedArticleImport(ActionEvent actionEvent) {
        Article article = currentArticle;
        try {
            article.setContent(parserWebsite.ParserArticle(article.getLink())); // Call the parser

            article.setImage(parserWebsite.ParserImage(article.getLink())); //Add Picture
            article.setDefaultThumbnail(website.getDefaultThumbnail()); // add thumbnail
            article.setSource(website.getSourceArticle()); // set the Source
            article.setGeolocation(website.getGeolocation());
            article.setVideo(parserWebsite.ParserVideo(article.getLink()));

            articleService.addArticle(article);
            showAlert("Article Added","Information");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Enum of different of website urls
     */
    public enum WebsiteSource {

        Guardian("TheGuardian"),
        LePoint("Lepoint"),
        LeMonde("LeMonde"),
        LeFigaro("LeFigaro"),
        RTL("RTLinfo");

        private String source;

        WebsiteSource(String s) {
            this.source = s;
        }
    }

    /**
     * Enum of different of website urls
     */
    public enum WebsiteCategory {

        Actualite("Actualite"),
        Culture("Culture"),
        Economie("Economie"),
        Belgique("Belgique"),
        Environnement("Environnement"),
        International("International"),
        Politique("Politique"),
        Sante("Sante"),
        Sport("Sport"),
        Technologies("Technologies");

        private String category;

        WebsiteCategory(String c) {
            this.category = c;
        }
    }

    /**
     * @param source is the website that the user want article from
     *               Create the right Object for the right website
     */

    private void CreateObjectSource(String source) {

        if (source.equals(Guardian.source)) {
            website = new Website(
                    Arrays.asList(
                            "https://www.theguardian.com/uk/business/rss",
                            "https://www.theguardian.com/uk/culture/rss",
                            "https://www.theguardian.com/uk/environment/rss",
                            "https://www.theguardian.com/uk/technology/rss",
                            "https://www.theguardian.com/uk/sport/rss",
                            "https://www.theguardian.com/profile/editorial/rss",
                            "https://www.theguardian.com/lifeandstyle/health-and-wellbeing/rss",
                            "https://www.theguardian.com/international/rss",
                            "https://www.theguardian.com/world/rss"
                    ),
                    Arrays.asList(
                            "Actualite",
                            "Culture",
                            "Politique",
                            "Environnement",
                            "International",
                            "Sante",
                            "Sport",
                            "Economie",
                            "Technolgies"),
                    "https://assets.guim.co.uk/images/eada8aa27c12fe2d5afa3a89d3fbae0d/fallback-logo.png",
                    "TheGuardian Media",
                    "Royaume Unis");
        } else if (source.equals(LePoint.source)) {
            website = new Website(
                    Arrays.asList("https://www.lepoint.fr/24h-infos/rss.xml",
                            "https://www.lepoint.fr/culture/rss.xml",
                            "https://www.lepoint.fr/politique/rss.xml",
                            "https://www.lepoint.fr/24h-infos/rss.xml",
                            "https://www.lepoint.fr/sport/rss.xml",
                            "https://www.lepoint.fr/economie/rss.xml",
                            "https://www.lepoint.fr/high-tech-internet/planete-appli/rss.xml"),
                    Arrays.asList(
                            "Actualite",
                            "Culture",
                            "Politique",
                            "International",
                            "Sport",
                            "Economie",
                            "Technologies"),
                    "https://pbs.twimg.com/profile_images/783607856574631936/oqc6S_6P_400x400.jpg",
                    "@LePoint.fr (LePoint Media)",
                    "France");
        } else if (source.equals(LeMonde.source)) {
            website = new Website(
                    Arrays.asList(
                            "https://www.lemonde.fr/m-actu/rss_full.xml",
                            "https://www.lemonde.fr/culture/rss_full.xml",
                            "https://www.lemonde.fr/politique/rss_full.xml",
                            "https://www.lemonde.fr/planete/rss_full.xml",
                            "https://www.lemonde.fr/international/rss_full.xml",
                            "https://www.lemonde.fr/sante/rss_full.xml",
                            "https://www.lemonde.fr/sport/rss_full.xml",
                            "https://www.lemonde.fr/economie/rss_full.xml",
                            "https://www.lemonde.fr/technologies/rss_full.xml"),
                    Arrays.asList(
                            "Actualite",
                            "Culture",
                            "Politique",
                            "Environnement",
                            "International",
                            "Sante",
                            "Sport",
                            "Economie",
                            "Technolgies"),
                    "https://www.acpm.fr/var/ojd/storage/files/logos/A/logo_5691.png",
                    "@LeMonde.fr (LeMonde Media)",
                    "France");
        } else if (source.equals(LeFigaro.source)) {
            website = new Website(
                    Arrays.asList(
                            "http://sport24.lefigaro.fr/rssfeeds/sport24-accueil.xml",
                            "http://www.lefigaro.fr/rss/figaro_culture.xml",
                            "http://www.lefigaro.fr/rss/figaro_actualites.xml",
                            "http://www.lefigaro.fr/rss/figaro_international.xml",
                            "http://www.lefigaro.fr/rss/figaro_sante.xml",
                            "http://www.lefigaro.fr/rss/figaro_politique.xml",
                            "http://www.lefigaro.fr/rss/figaro_economie.xml",
                            "http://www.lefigaro.fr/rss/figaro_secteur_high-tech.xml",
                            "http://www.lefigaro.fr/rss/figaro_sciences.xml"),
                    Arrays.asList(
                            "Sport",
                            "Culture",
                            "Actualite",
                            "International",
                            "Sante",
                            "Politique",
                            "Economie",
                            "Technologies",
                            "Environnement"),
                    "http://www.agassac.com/files/cache/article/files/files/0/1/4/8/0.jpg",
                    "@LeFigaro.fr (LeFigaro Media)",
                    "France");
        } else if (source.equals(RTL.source)) {
            website = new Website(
                    Arrays.asList("https://feeds.feedburner.com/rtlinfo/belgique",
                            "https://feeds.feedburner.com/RTLEconomie",
                            "https://feeds.feedburner.com/RTLInternational",
                            "https://feeds.feedburner.com/Rtlinfos-ALaUne",
                            "https://feeds.feedburner.com/RTLSports"),
                    Arrays.asList("Belgique",
                            "Economie",
                            "International",
                            "Actualite",
                            "Sport"),
                    "https://www.rtl.be/info/GED/00030000/37100/37148.jpg",
                    "newmedia@rtl.be (RTL NewMedia)",
                    "Belgique");
        }
    }

    /**
     * Take all article available
     */
    private void AddAllArticleAvailable() {
        RSSFeedParser parser = new RSSFeedParser(website.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));

        availableArticle = parser.readRSS();


    }

    /**
     * Function that will allow us to have an ArticleService object with all the available article
     * needed in order to use Observable (call update when the object change)
     */
    private void changeToArticleService() {
        availableArticle.forEach(article -> {
            allAvailableArticle.addArticle(article);
        });
    }


    /**
     * Static because we'll use it in ChooseArticleController
     *
     * @return all the AvailableArticle
     **/

    public static ArrayList<Article> getAvailableArticle() {
        return availableArticle;
    }

    /**
     * Handler when Add button is pressed
     */


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
        availableArticle = new ArrayList<>();
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
        displayMap();

    }

    /**
     * Shows an alert with a custom title & message
     *
     * @param message Body message
     * @param title   Header message
     */
    public static void showAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

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

            // sample code showing how to use the Java API

            map.addLayer(new Circle(new LatLng(51.508, -0.11), 500,
                    new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5)
            ).bindPopup("I am a Circle"));

            map.addLayer(new Polygon(new LatLng[]{
                    new LatLng(51.509, -0.08),
                    new LatLng(51.503, -0.06),
                    new LatLng(51.51, -0.047)
            }).bindPopup("I am a Polygon"));


        });


        mapcontainer.getChildren().add(webView);

        // a regular JavaFX ListView
        ListView<Address> listView = new ListView<>();
        listView.getItems().addAll(new Address("Toni", 48.1322840, 11.5361690),
                new Address("Jarda", 50.0284060, 14.4934400),
                new Address("JUG Münster", 51.94906770000001, 7.613701100000071));
        // we listen for the selected item and update the map accordingly
        // as a demo of how to interact between JavaFX and DukeScript
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Address>() {
            @Override
            public void changed(ObservableValue<? extends Address> ov, Address old_val, final Address new_val) {
                FXBrowsers.runInBrowser(webView, new Runnable() {
                    @Override
                    public void run() {
                        LatLng pos = new LatLng(new_val.getLat(), new_val.getLng());
                        map.setView(pos, 20);
                        map.openPopup("Here is " + new_val, pos);
                    }
                });
            }
        });


    }


}
