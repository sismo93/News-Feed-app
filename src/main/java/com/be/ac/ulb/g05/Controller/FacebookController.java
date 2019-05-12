package com.be.ac.ulb.g05.Controller;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


/**
 * @author @iyamani
 * @codereview @otrangan
 * Allow us to handle the case when the user want to connect to facebook
 */
public class FacebookController extends AbstractController {

    /**
     * Media View
     */
    public BorderPane mediaView;

    /**
     * URL when we succeed the login
     */
    private static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";

    /**
     * Web Engine
     */
    private WebEngine webEngine;

    /**
     * Code
     */
    private String code;


    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    @Override
    public void setupView() {
        WebView webView = new WebView();
        this.webEngine = new WebEngine();
        webEngine = webView.getEngine();
        showLogin("401846287318859","3405fe498624fca379bf6c53846e0d56");
        mediaView.setCenter(webView);
    }

    /**
     * @param appId
     * @param appSecret
     * Show the login interface and allow us to connect to facebook
     */
    public void showLogin(String appId, String appSecret) {
        DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
        ScopeBuilder scopes = new ScopeBuilder();
        String loadUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, scopes);
        webEngine.load(loadUrl + "&display=popup&response_type=code");
        webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
                    if (newValue != Worker.State.SUCCEEDED) {
                        return;
                    }
                    String myUrl = webEngine.getLocation();

                    if ("https://www.facebook.com/dialog/close".equals(myUrl)) {
                      System.exit(0);
                    }

                    if (myUrl.startsWith(SUCCESS_URL)) { //connexion succesful
                      int pos = myUrl.indexOf("code=");
                      code = myUrl.substring(pos + "code=".length());
                      FacebookClient.AccessToken token = facebookClient.obtainUserAccessToken(appId,
                              appSecret, SUCCESS_URL, code);

                      Router.Instance().changeView(Router.Views.FacebookData);
                    }
                });

    }

}
