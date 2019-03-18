package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;
import java.util.HashMap;


public class Feed {

    public HashMap<String, FeedMessage> feedMessages;

    public Feed() {
        feedMessages = new HashMap<>();
    }

    public void addFeedMessage(FeedMessage feedmessage){
        feedMessages.put(feedmessage.getTitle(), feedmessage);
    }

    public void deleteFeedMessage(FeedMessage feedMessage){
        feedMessages.remove(feedMessage.getTitle());
    }

    public FeedMessage getFeedMessage(String title){
        return feedMessages.get(title);
    }

    public ArrayList<FeedMessage> getFeedMessages(){
        return  new ArrayList<FeedMessage>(feedMessages.values());
    }
}
