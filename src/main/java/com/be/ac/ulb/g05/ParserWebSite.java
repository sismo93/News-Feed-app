package com.be.ac.ulb.g05;


import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author @Mouscb
 * @codereview @MnrBn
 *
 * Class that allow us to parse a website
 */
public class ParserWebSite {


    /**
     * @param url
     * @return text corresponding to the article
     * @throws IOException
     * Parse the website
     */
    public String ParserArticle(String url) throws IOException {
        String article="";

        Document doc;
        if(ConnectionTest(url)) {
            doc = Jsoup.connect(url).get();
        } else {
            return "";
        }

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

    /**
     * @param url
     * @return a string corresponding to the url of the image
     * @throws IOException
     */
    public String ParserImage(String url) throws IOException {
        Document doc;
        if(ConnectionTest(url)) {
            doc = Jsoup.connect(url).get();
        } else {
            return "";
        }


        ArrayList<String> listImages;

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

        if (images.size()>0) { // check if there is any picture
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
                if (imageLePoint.contains("/images/")) {
                    pic = "https://www.lepoint.fr";
                    pic += image.attr("src");

                }
            }
        }


        return pic;
    }
    
    private boolean ConnectionTest(String url) throws IOException {
        try {
            Connection.Response doc = Jsoup.connect(url).execute();
        } catch (HttpStatusException e){
            System.out.println("URL not found \n"+e);
            return false;
        }
        return true;
    }

}
