package com.be.ac.ulb.g05.Controller;


import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class TwitterController extends AbstractTwitterController {

    public TextField addByTagLabel;

    public void onSearchByTagPressed(ActionEvent actionEvent) {
        String tag = addByTagLabel.getText();
        if (!tag.isEmpty()) {
            try {
                twitterService.searchBy(tag);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
    }

    public void onSyncTimelinePressed(ActionEvent actionEvent) {
        try {
            twitterService.syncTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
