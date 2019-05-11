package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Website;
import javafx.scene.control.Alert;

import java.util.Arrays;

import static com.be.ac.ulb.g05.Controller.AddController.WebsiteSource.*;

public class AddController extends AbstractController {






    /**
     * Enum of different of website urls
     */
    public enum WebsiteSource {

        Guardian("TheGuardian"),
        LePoint("Lepoint"),
        LeMonde("LeMonde"),
        LeFigaro("LeFigaro"),
        RTL("RTLinfo");

        protected String source;

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

        protected String category;

        WebsiteCategory(String c) {
            this.category = c;
        }
    }

    public Website CreateObjectSource(String source) {

        if (source.equals(Guardian.source)) {
            return new Website(
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
                    "Royaume Unis",
                    51.5085,
                    -0.1257);
        } else if (source.equals(LePoint.source)) {
            return  new Website(
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
                    "France",
                    44.8404,
                    -0.5805);
        } else if (source.equals(LeMonde.source)) {
            return  new Website(
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
                    "France",
                    48.8534,
                    2.3488 );
        } else if (source.equals(LeFigaro.source)) {
            return  new Website(
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
                    "France",
                    45.75,
                    4.85 );
        } else  {
            return new Website(
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
                    "Belgique",
                    50.8466,
                    4.3528 );
        }
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
