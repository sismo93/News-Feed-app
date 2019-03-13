package be.ac.ulb.infof307.g05;


import java.util.Arrays;


public class LeMonde extends WebSite{


    public LeMonde(){

        super.urlList= Arrays.asList("https://www.lemonde.fr/m-actu/rss_full.xml",
                "https://www.lemonde.fr/culture/rss_full.xml",
                "https://www.lemonde.fr/politique/rss_full.xml",
                "https://www.lemonde.fr/planete/rss_full.xml",
                "https://www.lemonde.fr/international/rss_full.xml",
                "https://www.lemonde.fr/sante/rss_full.xml",
                "https://www.lemonde.fr/sport/rss_full.xml",
                "https://www.lemonde.fr/economie/rss_full.xml",
                "https://www.lemonde.fr/technologies/rss_full.xml");

        super.categoryList = Arrays.asList("Actualite","Culture","Politique","Environnement","International",
                "Sante","Sport","Economie","Technolgies");
        super.initDico();
    }



}
