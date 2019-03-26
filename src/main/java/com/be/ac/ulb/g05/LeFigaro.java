package com.be.ac.ulb.g05;

import java.util.Arrays;

public class LeFigaro extends Website {


    public LeFigaro() {

        super.urlList = Arrays.asList("http://sport24.lefigaro.fr/rssfeeds/sport24-accueil.xml",
                "http://www.lefigaro.fr/rss/figaro_culture.xml",
                "http://www.lefigaro.fr/rss/figaro_actualites.xml",
                "http://www.lefigaro.fr/rss/figaro_international.xml",
                "http://www.lefigaro.fr/rss/figaro_sante.xml",
                "http://www.lefigaro.fr/rss/figaro_politique.xml",
                "http://www.lefigaro.fr/rss/figaro_economie.xml",
                "http://www.lefigaro.fr/rss/figaro_secteur_high-tech.xml",
                "http://www.lefigaro.fr/rss/figaro_sciences.xml");

        super.categoryList = Arrays.asList("Sport", "Culture", "Actualite","International",
                "Sante","Politique","Economie","Technologies","Environnement");
        super.defaultThumbnail = "http://www.agassac.com/files/cache/article/files/files/0/1/4/8/0.jpg";
        super.sourceArticle = "@LeFigaro.fr (LeFigaro Media)";
        super.geolocation = "France";
        super.initDico();
    }
}
