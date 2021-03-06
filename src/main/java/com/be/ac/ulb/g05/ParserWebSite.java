package com.be.ac.ulb.g05;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

/**
 * @author @Mouscb
 * @codereview @MnrBn
 * <p>
 * Class that allow us to parse a website
 */
public class ParserWebSite {

    /**
     * @param url article url
     * @return text corresponding to the article
     * @throws IOException Parse the website
     */
    public String ParserArticle(String url) throws IOException {
        StringBuilder article = new StringBuilder();

        Document doc;

        Jsoup.connect(url).execute();

        doc = Jsoup.connect(url).get();


        Elements body = doc.select("article");

        body = body.select("p");
        boolean access = true;
        for (Element test : body) {
            if (test.text().equals("Les plus lus") || !access) {
                access = false;
            } else {
                article.append(test.text());
                article.append("\n");
            }
        }

        return article.toString();

    }

    /**
     * @param url image url
     * @return a string corresponding to the url of the image
     * @throws IOException if IO problems
     */
    public String ParserImage(String url) throws IOException {
        Document doc;

        Jsoup.connect(url).execute();

        doc = Jsoup.connect(url).get();

        Elements images = doc.select("article");
        if (url.contains("rtl") || url.contains("Rtl") || url.contains("RTL")) {
            images = images.select("div [class=w-content-details-article-img]");
        }
        if (url.contains("lefigaro")) {
            images = images.select("img[srcset~=(?i)\\.(png|jpeg|gif|jpg)]");

        } else {
            images = images.select("img[src~=(?i)\\.(png|jpeg|gif|jpg)]");
        }


        String pic = "";

        if (images.size() > 0) { // check if there is any picture
            Element image = images.get(0);

            if (url.contains("rtl") || url.contains("Rtl") || url.contains("RTL") || url.contains("lemonde") || url.contains("theguardian")) {
                pic = image.attr("src");


            } else if (url.contains("lefigaro")) {
                pic = image.attr("srcset");
                int index = Integer.parseInt(String.valueOf(pic.indexOf(",")));
                if (index != -1) {
                    pic = pic.substring(0, index - 5);
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

    /**
     * @param url video url
     * @return a string corresponding to the url of the video
     * @throws IOException if IO problems
     */
    @SuppressWarnings("ConstantConditions")
    public String ParserVideo(String url) throws IOException {
        String doc = Jsoup.connect(url).get().toString();
        Document document = Jsoup.parse(doc);
        int size = 0;
        Elements links = null;
        if (url.contains("lemonde")) {
            links = document.select("div[class=article__video-container-main]");
            size = 7;
            if (links.size() == 0) {
                links = document.select("div[class=article__video-container]");

            }

        } else if (url.contains("theguardian")) {
            links = document.select("div[data-media-atom-id]");
            size = 8;

        }

        String ytLink = "";


        if (url.contains("lemonde") || url.contains("theguardian")) {
            Elements l = null;
            if (links != null) {
                l = links.select("div[id~=(?)]");
            }

            if (l.size() != 0) {
                ytLink = "https://www.youtube.com/watch?v=";
                ytLink += l.attr("id").substring(size);
            }
        }
        return ytLink;
    }
}
