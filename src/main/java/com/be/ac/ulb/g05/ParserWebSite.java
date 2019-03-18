package com.be.ac.ulb.g05;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
        Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|jpg)]");
        String pic= "";
        boolean accees = true;
        for (Element image : images) {
            if ((url.contains("lefigaro") || url.contains("rtl") ||url.contains("Rtl") ||url.contains("RTL") || url.contains("theguardian")) && accees) {
                pic = image.attr("src");
                accees = false;
            } else if (url.contains("lemonde")) {
                pic = image.attr("src");

            } else {
                String imageLePoint = image.attr("src").substring(0, 8);

                if (imageLePoint.contains("/images/")) {
                    pic = "https://www.lepoint.fr";
                    pic += image.attr("src");
                }

            }
        }
        return pic;
    }

}
