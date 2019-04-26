package com.be.ac.ulb.g05.Model;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.*;

public class TwitterService extends Observable {

    private static final String CONSUMER_KEY = "T5DQdNCMJWzXZazViUe06yjrX";
    private static final String CONSUMER_SECRET = "753z7k9uhcEPsPH0ANnYsrcn19KaiEEzzieElARDuG3MPyTjs3";
    public static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";
    public static final String AUTHORIZED_URL = "https://api.twitter.com/oauth/authorize";

    private List<Status> statuses;

    private Twitter twitter;

    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        statuses = new ArrayList<>();
    }


    public AccessToken getAccessToken(String pin) throws TwitterException {
        return twitter.getOAuthAccessToken(twitter.getOAuthRequestToken(), pin);
    }

    private RequestToken getRequestToken() throws TwitterException {
        return twitter.getOAuthRequestToken();
    }

    public String getAuthUrl() throws TwitterException {
        RequestToken requestToken = getRequestToken();
        return requestToken.getAuthenticationURL();
    }

    public void setAccessToken(AccessToken accessToken) {
        twitter.setOAuthAccessToken(accessToken);
    }

    public void syncTimeline() throws TwitterException {
        setChanged();
        statuses.addAll(twitter.getHomeTimeline());
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" + status.getText());
        }
        notifyObservers();
    }

    public void searchBy(String tag) throws TwitterException {
        setChanged();
        statuses.addAll(twitter.getHomeTimeline());
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" + status.getText());
        }
        notifyObservers();
    }

    public ArrayList<Status> getStatusAll() {
        return new ArrayList<>(statuses);
    }

}
