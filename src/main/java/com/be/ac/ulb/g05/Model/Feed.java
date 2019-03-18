package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;


public class Feed extends Observable {

    private HashMap<String, FeedMessage> feedMessages;

    public Feed() {
        feedMessages = new HashMap<>();
    }

    public void addFeedMessage(FeedMessage feedmessage){
        feedMessages.put(feedmessage.getTitle(), feedmessage);
        notifyObservers();
    }

    public void deleteFeedMessage(FeedMessage feedMessage){
        feedMessages.remove(feedMessage.getTitle());
        notifyObservers();
    }

    public FeedMessage getFeedMessage(String title){
        return feedMessages.get(title);
    }

    public ArrayList<FeedMessage> getFeedMessages(){
        return  new ArrayList<FeedMessage>(feedMessages.values());
    }
}
