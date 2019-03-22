package com.be.ac.ulb.g05;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ParserWebSite {


    public String ParserArticle(String url) throws IOException {
        String article="";

        Document doc = Jsoup.connect(url).get();

        Elements body = doc.select("article");

        body = body.select("p");
        boolean access = true;
        for (Element test : body) {
            if (test.text().equals("Les plus lus") || !access ){
                access = false;
            }
            else {
                article += test.text();
                article += "\n";
            }
        }

        return article;

    }
    public String ParserImage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();


        ArrayList<String> listImages;
        listImages = new ArrayList<String>();

        Elements images = doc.select("article");
        if ( url.contains("rtl") ||url.contains("Rtl") ||url.contains("RTL")){
            images = images.select("div [class=w-content-details-article-img]");
        }
        if (url.contains("lefigaro")){
            images = images.select("img[srcset~=(?i)\\.(png|jpeg|gif|jpg)]");

        }
        else {
            images = images.select("img[src~=(?i)\\.(png|jpeg|gif|jpg)]");
        }


        String pic= "";

        if (images.size()>0) {
            Element image = images.get(0);

            if (url.contains("rtl") || url.contains("Rtl") || url.contains("RTL") || url.contains("lemonde") || url.contains("theguardian")) {
                pic = image.attr("src");


            } else if (url.contains("lefigaro")) {
                pic = image.attr("srcset");
                int index = Integer.parseInt(String.valueOf(pic.indexOf(",")));
                if (index != -1){
                    pic = pic.substring(0,index-5);
                }


            } else {
                String imageLePoint = image.attr("src").substring(0, 8);
                ;
                if (imageLePoint.contains("/images/")) {
                    pic = "https://www.lepoint.fr";
                    pic += image.attr("src");

                }
            }
        }


        return pic;
    }


}
