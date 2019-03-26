package com.be.ac.ulb.g05;


import java.util.Arrays;


/**
 * @author @MnrBn
 * @codereview @Mouscb
 * inherited from Website, modified to match data from the "LeMonde" site
 *
 */
public class LeMonde extends Website {


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
        super.defaultThumbnail="http://www.acpm.fr/var/ojd/storage/files/logos/A/logo_5691.png";
        super.sourceArticle = "@LeMonde.fr (LeMonde Media)";
        super.geolocation = "France";
        super.initDico();
    }



}
