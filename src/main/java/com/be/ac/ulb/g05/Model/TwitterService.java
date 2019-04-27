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
    private boolean authenticated;

    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        statuses = new ArrayList<>();
        authenticated = false;
    }

    public AccessToken getAccessToken(String pin) throws TwitterException {
        return twitter.getOAuthAccessToken(pin);
    }



    public void postTweet(String text) throws TwitterException,IllegalStateException {
        twitter.updateStatus(text);
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
        ArrayList<Article> articles = new ArrayList<>();
        Article article;
        for (Status status : statuses) {
            article = new Article();

            article.setPubDate(status.getCreatedAt().toString());
            article.setSource(status.getUser().getName());
            article.setContent(status.getText());
            article.setContent(status.getText());
            article.setTitle("twitter feed");
            articles.add(article);
        }

        return articles;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
