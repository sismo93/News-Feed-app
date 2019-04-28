package com.be.ac.ulb.g05.Controller;

import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author @@sismo93
 * @codereview @otrangan
 * Allow us to add a video on the article
 */
public class MediaViewController extends AbstractController {

    public HBox hboxView;

    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    @Override
    public void setupView() {
        String url = articleService.getArticle().getVideo();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);


        hboxView.getChildren().add(webView);

    }
}
