package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;


public class ArticleService extends Observable {

    private HashMap<String, Article> feedMessages;

    public ArticleService() {
        feedMessages = new HashMap<>();
    }

    public void addFeedMessage(Article feedmessage){
        feedMessages.put(feedmessage.getTitle(), feedmessage);
        notifyObservers();
    }

    public void deleteFeedMessage(Article feedMessage){
        feedMessages.remove(feedMessage.getTitle());
        notifyObservers();
    }

    public Article getFeedMessage(String title){
        return feedMessages.get(title);
    }

    public ArrayList<Article> getFeedMessages(){
        return  new ArrayList<Article>(feedMessages.values());
    }
}
