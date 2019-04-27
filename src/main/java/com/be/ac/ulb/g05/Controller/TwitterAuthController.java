package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.TwitterService;
import javafx.concurrent.Worker;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import com.be.ac.ulb.g05.Controller.Router.Views;


public class TwitterAuthController extends AbstractTwitterController {

    public BorderPane mediaView;
    private WebEngine webEngine;


    @Override
    public void setupView() {

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
            loadAuthPage();
        } catch (TwitterException e) {
            e.printStackTrace();
            if (401 == e.getStatusCode()) {
                System.out.println("Unable to get the access token.");
            }
        }
    }

    private void loadAuthPage() throws TwitterException {
        String url = twitterService.getAuthUrl();
        webEngine.load(url);
    }

    private void onWebEngineLocationChanged() throws TwitterException {

        if (!webEngine.getLocation().equals(TwitterService.AUTHORIZED_URL) && !webEngine.getLocation().equals(TwitterService.AUTHENTICATE_URL))
            return;


        String pin = (String) webEngine.executeScript("(document.getElementsByTagName(\"kbd\")[0])?document.getElementsByTagName(\"kbd\")[0].innerText : ''");


        if (!pin.isEmpty()) {
            System.out.println("pin" + pin);
            AccessToken accessToken = twitterService.getAccessToken(pin);
            twitterService.setAccessToken(accessToken);
            Router.Instance().changeView(Views.Twitter);
        } else {
            Router.Instance().changeView(Views.Menu);
        }
    }

}
