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


public class FacebookController extends AbstractController {
    public BorderPane mediaView;
    private static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";
    private WebEngine webEngine;
    private String code;



    @Override
    public void setupView() {
        WebView webView = new WebView();
        this.webEngine = new WebEngine();
        webEngine = webView.getEngine();
        showLogin("401846287318859","3405fe498624fca379bf6c53846e0d56");
        mediaView.setCenter(webView);
    }

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
                      System.out.println("dialog closed");
                      System.exit(0);
                    }

                    if (myUrl.startsWith(SUCCESS_URL)) { // connection reussite
                      int pos = myUrl.indexOf("code=");
                      code = myUrl.substring(pos + "code=".length());
                      FacebookClient.AccessToken token = facebookClient.obtainUserAccessToken(appId,
                              appSecret, SUCCESS_URL, code);

                      Router.Instance().changeView(Router.Views.FacebookData);
                    }
                });

    }

}
