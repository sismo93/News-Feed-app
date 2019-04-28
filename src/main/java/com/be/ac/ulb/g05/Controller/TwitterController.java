package com.be.ac.ulb.g05.Controller;



import javafx.scene.control.TextField;

import twitter4j.Query;
import twitter4j.TwitterException;


import static com.be.ac.ulb.g05.Controller.AddController.showAlert;


public class TwitterController extends AbstractTwitterController {

    public TextField addByTagLabel;
    public TextField addByUsernameLabel;

    public void onSyncTimelinePressed() {
        try {
            twitterService.syncTimeline();
            showAlert("TimeLine has been imported","Information");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public void onSearchByTagPressed( ) {
        String tag = addByTagLabel.getText();
        if (tag.isEmpty()) return;

        searchOnTwitter("#" + tag);
        showAlert("Added some tweet talking about "+tag,"Information");
    }

    public void onSearchByUsernamePressed( ) {
        String username = addByUsernameLabel.getText();
        if (username.isEmpty()) return;
        try {
            System.out.println("labas");
            twitterService.searchUser(username);
            showAlert("Now you follow @"+username +" \n Check your timeline maybe he/she tweeted","Information");
        } catch (TwitterException e) {
            showAlert("Failed to find "+ username,"Information");
        }
    }

    private void searchOnTwitter(String queryString) {
        Query query = new Query(queryString);
        try {
            twitterService.searchBy(query);

        } catch (TwitterException e) {
            showAlert("Failed to search tweets: " + e.getMessage(),"Information");
        }
    }
}
