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
import java.util.Arrays;
import java.util.Optional;

import static com.be.ac.ulb.g05.Controller.AddController.WebsiteCategory.*;
import static com.be.ac.ulb.g05.Controller.AddController.WebsiteSource.*;


/**
 * Controller of the Add View
 *
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
     * Enum of different of wbesite urls
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
     * Enum of different of wbesite urls
     */
    public enum WebsiteCategory{

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
     * @return an Website object
     * Create the right Object for the right website
     */

    public void CreateObjectSource(String source) {

        if (source.equals(Guardian.source)) {
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
                    "https://assets.guim.co.uk/images/eada8aa27c12fe2d5afa3a89d3fbae0d/fallback-logo.png",
                    "TheGuardian Media",
                    "Royaume Unis");
        }
        else if (source.equals(LePoint.source)) {
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
        }
        else if (source.equals(LeMonde.source)) {
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
                    "http://www.acpm.fr/var/ojd/storage/files/logos/A/logo_5691.png",
                    "@LeMonde.fr (LeMonde Media)",
                    "France");
        }
        else if (source.equals(LeFigaro.source)) {
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
        }
        else if (source.equals(RTL.source)) {
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
     * Add Article to the list
     * Take the same number of article that the user want
     */

    public void AddCurrentArticleList() {
        RSSFeedParser parser = new RSSFeedParser(website.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));

        ArrayList<Article> articles = parser.readRSS();

        for (int i = 0; i < (int) ArticleNumberBox.getSelectionModel().getSelectedItem(); i++) { // add Article to the list
            CurrentArticleList.add(articles.get(i));
        }
    }


    /**
     * @param actionEvent Add the article to the feed
     */
    public void OnButtonPressed(ActionEvent actionEvent) throws IOException {
        if (CategoryBox.getSelectionModel().isEmpty()
                || ArticleNumberBox.getSelectionModel().isEmpty()
                || SourceArticleBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have to choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        CreateObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());


        if (!website.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This category doesnt exist on this website", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        AddCurrentArticleList(); // Add Article to the list

        ShowArticleFound();

    }

    /**
     * @param article       the article source
     * @param articleObject the article object
     *                      change the description if its TheGuardian or RTLINFO
     *                      because these 2 websites doesnt handle the description for an article
     */

    public void FixDescriptionError(String article, Article articleObject) {
        String source = (String) SourceArticleBox.getSelectionModel().getSelectedItem();
        if ((source.equals(Guardian.source)) || (source.equals(RTL.source))) {
            articleObject.setDescription(article.substring(0, 100));
        }
    }


    /**
     * @throws IOException Shows the articles found + title + description
     *                     The user can accept them or denied them
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


    /**
     * @param actionEvent Open the Menu View
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
     * <p>
     * Initialize the various controls on the screen
     */

    @Override
    public void setupView() {
        CurrentArticleList = new ArrayList<>();
        parserWebsite = new ParserWebSite();

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
