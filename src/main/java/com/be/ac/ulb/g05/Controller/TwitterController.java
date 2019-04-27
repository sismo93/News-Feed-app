package com.be.ac.ulb.g05.Controller;


import javafx.event.ActionEvent;

import javafx.scene.control.TextField;

import twitter4j.TwitterException;


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
