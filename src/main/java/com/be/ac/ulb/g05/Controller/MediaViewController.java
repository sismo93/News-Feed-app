package com.be.ac.ulb.g05.Controller;

import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MediaViewController extends Controller {

    public HBox hboxView;

    @Override
    public void setupView() {
        String url = articleService.getArticle().getVideo();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);


        hboxView.getChildren().add(webView);

    }
}
