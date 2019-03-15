package be.ac.ulb.infof307.g05;


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
}
