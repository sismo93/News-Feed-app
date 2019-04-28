package com.be.ac.ulb.g05.Controller;


import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

import twitter4j.Query;
import twitter4j.TwitterException;


public class TwitterController extends AbstractTwitterController {

    public TextField addByTagLabel;
    public TextField addByUsernameLabel;

    public void onSyncTimelinePressed(ActionEvent actionEvent) {
        try {
            twitterService.syncTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public void onSearchByTagPressed(ActionEvent actionEvent) {
        String tag = addByTagLabel.getText();
        if (tag.isEmpty()) return;

        searchOnTwitter("#" + tag);
    }

    public void onSearchByUsernamePressed(ActionEvent actionEvent) {
        String username = addByTagLabel.getText();
        if (username.isEmpty()) return;
        searchOnTwitter("source:" + username);
    }

    private void searchOnTwitter(String queryString) {
        Query query = new Query(queryString);
        try {
            twitterService.searchBy(query);
        } catch (TwitterException e) {
            System.out.println("Failed to search tweets: " + e.getMessage());
        }
    }
}
