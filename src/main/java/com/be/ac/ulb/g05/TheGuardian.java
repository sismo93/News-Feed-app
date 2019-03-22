package com.be.ac.ulb.g05;

import java.util.Arrays;

public class TheGuardian extends WebSite{


    public TheGuardian(){

        super.urlList= Arrays.asList("https://www.theguardian.com/uk/business/rss",
                "https://www.theguardian.com/uk/culture/rss",
                "https://www.theguardian.com/uk/environment/rss",
                "https://www.theguardian.com/uk/technology/rss",
                "https://www.theguardian.com/uk/sport/rss",
                "https://www.theguardian.com/profile/editorial/rss",
                "https://www.theguardian.com/lifeandstyle/health-and-wellbeing/rss",
                "https://www.theguardian.com/international/rss",
                "https://www.theguardian.com/world/rss");

        super.categoryList = Arrays.asList("Economie","Culture","Environnement","Technolgies","Sport",
                "Politique","Sante","International","Actualite");
        super.defaultThumbnail = "https://assets.guim.co.uk/images/eada8aa27c12fe2d5afa3a89d3fbae0d/fallback-logo.png";
        super.sourceArticle = "TheGuardian Media";
        super.geolocation = "Royaume Unis";
        super.initDico();
    }
}
