package com.be.ac.ulb.g05;

import java.util.Arrays;
/**
 * @author @MnrBn
 * @codereview @Mouscb
 * inherited from Website, modified to match data from the "LePoint" site
 *
 */
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
        super.defaultThumbnail = "https://pbs.twimg.com/profile_images/783607856574631936/oqc6S_6P_400x400.jpg";
        super.sourceArticle = "@LePoint.fr (LePoint Media)";
        super.geolocation = "France";
        super.initDico();
    }

}
