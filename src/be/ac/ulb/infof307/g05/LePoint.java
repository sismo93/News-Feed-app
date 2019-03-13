package be.ac.ulb.infof307.g05;

import java.util.Arrays;

public class LePoint extends WebSite{

    public LePoint(){

        super.urlList= Arrays.asList("https://www.lepoint.fr/24h-infos/rss.xml",
                "https://www.lepoint.fr/culture/rss.xml",
                "https://www.lepoint.fr/politique/rss.xml",
                "https://www.lepoint.fr/24h-infos/rss.xml",
                "https://www.lepoint.fr/sport/rss.xml",
                "https://www.lepoint.fr/economie/rss.xml",
                "https://www.lepoint.fr/high-tech-internet/planete-appli/rss.xml");
        super.categoryList = Arrays.asList("Actualite","Culture","Politique","International",
                "Sport","Economie","Technologies");
        super.initDico();
    }

}
