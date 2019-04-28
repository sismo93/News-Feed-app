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

    /**
     * Title
     */
    static final String TITLE = "title";

    /**
     * Description
     */
    static final String DESCRIPTION = "description";

    /**
     * Link
     */
    static final String LINK = "link";

    /**
     * Item
     */
    static final String ITEM = "item";

    /**
     * Publication date
     */
    static final String PUB_DATE = "pubDate";

    /**
     * URL
     */
    final URL url;

    /**
     * Constructor
     * @param feedUrl feed URL
     */
    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the RSS feed
     * @return an arrayList of article type
     */
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
                        article.addTag("Rss");
                        article.addTag("All");
                        articles.add(article);

                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    /**
     * returns the character data
     * @param event XML event
     * @param eventReader Event reader
     * @return the character data
     * @throws XMLStreamException if exception occurs
     */
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    /**
     * Input stream
     * @return url
     */
    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}