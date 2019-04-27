package com.be.ac.ulb.g05.Model;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.*;

public class TwitterService extends Observable {

    private static final String CONSUMER_KEY = "c5QH9G5KXl26uwUO83oN3omtD";
    private static final String CONSUMER_SECRET = "mwPhVED1YhsQgdEEnUQdA80aVFmgzUFEv7UnhccTAOH54yMlFd";
    public static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";
    public static final String AUTHORIZED_URL = "https://api.twitter.com/oauth/authorize";

    private List<Status> statuses;

    private Twitter twitter;
    private RequestToken requestToken;

    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        statuses = new ArrayList<>();

    }

    public AccessToken getAccessToken(String pin) throws TwitterException {
        return twitter.getOAuthAccessToken(pin);
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

    public ArrayList<Article> getStatusAll() {
        return new ArrayList<>();
    }

}
