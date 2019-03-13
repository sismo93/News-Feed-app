package be.ac.ulb.infof307.g05;

import java.util.Arrays;

public class SeptSurSept extends WebSite{


    public SeptSurSept(){

        super.urlList= Arrays.asList("https://www.7sur7.be/belgique/rss.xml",
                "https://www.7sur7.be/monde/rss.xml",
                "https://www.7sur7.be/you/sante/rss.xml",
                "https://www.7sur7.be/stars/culture/rss.xml",
                "https://www.7sur7.be/finance/rss.xml",
                "https://www.7sur7.be/sports/rss.xml",
                "https://www.7sur7.be/rss.xml",
                "https://www.7sur7.be/sosplanete/rss.xml");

        super.categoryList = Arrays.asList("Belgique","International","Sante","Culture","Economie",
                "Sport","Actualite","Environnement");
        super.initDico();
    }
}
