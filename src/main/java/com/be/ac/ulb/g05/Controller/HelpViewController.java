package com.be.ac.ulb.g05.Controller;


import javafx.fxml.FXML;
import javafx.scene.web.WebView;

/**
 * Help controller
 */
public class HelpViewController extends AbstractController {

    /**
     * Webview
     */
    @FXML
    public WebView webView;

    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    @Override
    public void setupView() {
        String embedVideo = "https://youtube.com/embed/PImqC9Lkt2w";
        webView.getEngine().load(embedVideo);
    }
}
