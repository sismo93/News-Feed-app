package com.be.ac.ulb.g05.Controller;

import javafx.scene.control.TextField;
import twitter4j.TwitterException;


/**
 * @author @borsalinoK
 * @codereview @otrangan
 */
public class TwitterController extends AbstractTwitterController {

    /**
     * FXML TextField
     */
    public TextField addByTagLabel;
    public TextField addByUsernameLabel;

    /**
     * add the twitter timeline to the article List
     */
    public void onSyncTimelinePressed() {
        try {
            twitterService.syncTimeline();
            Router.showAlert("TimeLine has been imported","Information");
        } catch (TwitterException e) {
            Router.showAlert("An error has occurred","Error");
        }
    }

    /**
     *search on twitter tweet talking about the queryString
     *will be added to the article list
     */
    public void onSearchByTagPressed( ) {
        String tag = addByTagLabel.getText();
        if (tag.isEmpty()) return;

        try {
            twitterService.searchBy(tag);

        } catch (TwitterException e) {
            Router.showAlert("Failed to search tweets: " + e.getMessage(),"Information");
        }
        Router.showAlert("Added some tweet talking about "+tag,"Information");
    }

    /**
     * follow the twitter account of the username
     */
    public void onSearchByUsernamePressed( ) {
        String username = addByUsernameLabel.getText();
        if (username.isEmpty()) return;
        try {
            twitterService.searchUser(username);
            Router.showAlert("Now you follow @"+username +" \n We added his last tweet go check out !","Information");
        } catch (TwitterException e) {
            Router.showAlert("Failed to find "+ username,"Information");
        }
    }

}
