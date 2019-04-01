package com.be.ac.ulb.g05;

import com.be.ac.ulb.g05.Model.Article;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Code that read a RSS feed.
 * @Mnrbn
 * codereview:@Mouscb
 */
public class RSSFeedParser {

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";

    static final String LINK = "link";

    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";

    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Article> readRSS() {
        Article article;
        ArrayList<Article> articles = new ArrayList<>();
        // Set header values intial to the empty string
        String description = "";
        String title = "";
        String link = "";
        String pubdate = "";
        // First create a new XMLInputFactory
        XMLInputFactory inputFactory;
        InputStream in;

        try {

            inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;

                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;

                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart().equals(ITEM)) {
                        article = new Article();
                        article.setTitle(title);
                        article.setDescription(description);
                        article.setLink(link);
                        article.setPubDate(pubdate);
                        articles.add(article);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}