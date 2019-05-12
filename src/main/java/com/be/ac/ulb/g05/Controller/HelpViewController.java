package com.be.ac.ulb.g05.Controller;


import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class HelpViewController extends AbstractController {

    @FXML
    public WebView webView;

    private String embedVideo = "https://youtube.com/embed/PImqC9Lkt2w";

    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    @Override
    public void setupView() {
        webView.getEngine().load(embedVideo);
    }


}
