package com.be.ac.ulb.g05.Controller;



import javafx.scene.control.TextField;

import twitter4j.Query;
import twitter4j.TwitterException;


import static com.be.ac.ulb.g05.Controller.AddController.showAlert;


/**
 * @author @borsalinoK
 * @codereview @otrangan
 */
public class TwitterController extends AbstractTwitterController {

    public TextField addByTagLabel;
    public TextField addByUsernameLabel;

    /**
     * add the twitter timeline to the article List
     */
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

    /**
     * follow the twitter account of the username
     */
    public void onSearchByUsernamePressed( ) {
        String username = addByUsernameLabel.getText();
        if (username.isEmpty()) return;
        try {
            twitterService.searchUser(username);
            showAlert("Now you follow @"+username +" \n Check your timeline maybe he/she tweeted","Information");
        } catch (TwitterException e) {
            showAlert("Failed to find "+ username,"Information");
        }
    }

    /**
     * @param queryString
     * search on twitter tweet talking about the queryString
     * will be added to the article list
     */
    private void searchOnTwitter(String queryString) {
        Query query = new Query(queryString);
        try {
            twitterService.searchBy(query);

        } catch (TwitterException e) {
            showAlert("Failed to search tweets: " + e.getMessage(),"Information");
        }
    }
}
