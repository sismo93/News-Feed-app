package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.TwitterService;
import javafx.concurrent.Worker;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import com.be.ac.ulb.g05.Controller.Router.Views;


/**
 * TwitterAuthController
 *
 * @author @borsalinoK
 * @codereview @Tanvir.Hoque
 */
public class TwitterAuthController extends AbstractTwitterController {

    /**
     * FXML media View
     */
    public BorderPane mediaView;

    /**
     * WebEngine
     */
    private WebEngine webEngine;

    /**
     * Sets up the view. Called the first time UI element is loaded
     */
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
                    Router.showAlert("An Error has occurred","Information");
                }
            }
        });
    }

    /**
     * Called whenever whenever the focus is on the view managed by the controller
     *
     */
    @Override
    public void onActive() {
        if(twitterService.isAuthenticated()){
            Router.Instance().changeView(Views.Twitter);
        } else {
            loadAuthPage();
        }
    }

    /**
     * fetch the corresponding url for twitter authentication
     */
    private void loadAuthPage() {
        String url;
        try {
            url = twitterService.getAuthUrl();
            webEngine.load(url);

        } catch (TwitterException e) {
            Router.showAlert("unable to get the authentication url", "error");
        }
    }

    /**
     * @throws TwitterException
     * callback on browser page loading.
     * Get the pin code
     * setup the access token
     * change view on success
     */
    private void onWebEngineLocationChanged() throws TwitterException {

        if (!webEngine.getLocation().equals(TwitterService.AUTHORIZED_URL) && !webEngine.getLocation().equals(TwitterService.AUTHENTICATE_URL))
            return;

        String pin = (String) webEngine.executeScript("(document.getElementsByTagName(\"kbd\")[0])?document.getElementsByTagName(\"kbd\")[0].innerText : ''");


        if (!pin.isEmpty()) {
            AccessToken accessToken = twitterService.getAccessToken(pin);
            twitterService.setAccessToken(accessToken);
            twitterService.setAuthenticated(true);
            Router.Instance().changeView(Views.Twitter);
        } else {
            Router.Instance().changeView(Views.Menu);
        }
    }


}
