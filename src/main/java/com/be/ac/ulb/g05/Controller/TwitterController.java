package com.be.ac.ulb.g05.Controller;


import javafx.concurrent.Worker;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TwitterController extends Controller {

    static final String CONSUMER_KEY = "c5QH9G5KXl26uwUO83oN3omtD";
    static final String CONSUMER_SECRET = "mwPhVED1YhsQgdEEnUQdA80aVFmgzUFEv7UnhccTAOH54yMlFd";
    static final String ACCESS_TOKEN = "859352798919577600-QPr0YdgOzVnCbGcK2UjQyeTY0uAUuVW";
    static final String ACCESS_TOKEN_SECRET = "5ptuAajiwYpAxBeGXl9yECq8ZTsvkiIC0fuVq9w9vsRwN";
    static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";
    static final String AUTHORIZED_URL = "https://api.twitter.com/oauth/authorize";
    private String pin = null;


    public BorderPane mediaView;
    private WebEngine webEngine;
    Twitter twitter;
    RequestToken requestToken = null;
    AccessToken accessToken = null;

    @Override
    public void setupView() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

        WebView webView = new WebView();
        mediaView.setCenter(webView);

        this.webEngine = new WebEngine();
        webEngine = webView.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                try {
                    onWebEngineLocationChanged();
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            }
        });


        try {
            requestToken = getRequestToken();
            webEngine.load(getAuthUrl(requestToken));

        } catch (TwitterException e) {
            e.printStackTrace();
            if (401 == e.getStatusCode()) {
                System.out.println("Unable to get the access token.");
            }
        }
    }

    private void onWebEngineLocationChanged() throws TwitterException {
        System.out.println(webEngine.getLocation() + " " + AUTHORIZED_URL);
        if (webEngine.getLocation().equals(AUTHORIZED_URL) || webEngine.getLocation().equals(AUTHENTICATE_URL)) {
            pin = (String) webEngine.executeScript("document.getElementsByTagName(\"kbd\")[0].innerText");
            accessToken = getAccessToken(requestToken, pin);
            setAccessToken(accessToken);
            getTimeline();
        }
    }

    private void getTimeline() throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" + status.getText());
        }
    }

    public AccessToken getAccessToken(RequestToken requestToken, String pin) throws TwitterException {
        return twitter.getOAuthAccessToken(requestToken, pin);
    }

    private RequestToken getRequestToken() throws TwitterException {
        return twitter.getOAuthRequestToken();
    }

    public String getAuthUrl(RequestToken requestToken) {
        return requestToken.getAuthenticationURL();
    }

    public void setAccessToken(AccessToken accessToken) {
        twitter.setOAuthAccessToken(accessToken);
        this.accessToken = accessToken;
    }
}
