package com.be.ac.ulb.g05;

import java.util.Arrays;


public class RTL extends WebSite {

    public RTL(){
        super.urlList= Arrays.asList("https://feeds.feedburner.com/rtlinfo/belgique",
                "https://feeds.feedburner.com/RTLEconomie",
                "https://feeds.feedburner.com/RTLInternational",
                "https://feeds.feedburner.com/Rtlinfos-ALaUne",
                "https://feeds.feedburner.com/RTLSports");

        super.categoryList = Arrays.asList("Belgique","Economie","International","Actualite",
                "Sport");
        super.initDico();
    }



}
