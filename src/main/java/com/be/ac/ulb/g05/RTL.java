package com.be.ac.ulb.g05;

import java.util.Arrays;

/**
 * @author @MnrBn
 * @codereview @Mouscb
 * inherited from Website, modified to match data from RTL site
 *
 */
public class RTL extends Website {

    public RTL(){
        super.urlList= Arrays.asList("https://feeds.feedburner.com/rtlinfo/belgique",
                "https://feeds.feedburner.com/RTLEconomie",
                "https://feeds.feedburner.com/RTLInternational",
                "https://feeds.feedburner.com/Rtlinfos-ALaUne",
                "https://feeds.feedburner.com/RTLSports");

        super.categoryList = Arrays.asList("Belgique","Economie","International","Actualite",
                "Sport");
        super.defaultThumbnail = "https://www.rtl.be/info/GED/00030000/37100/37148.jpg";
        super.sourceArticle = "newmedia@rtl.be (RTL NewMedia)";
        super.geolocation = "Belgique";
        super.initDico();
    }



}
