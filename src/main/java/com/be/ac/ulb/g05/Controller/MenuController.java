package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.Model.Feed;
import javafx.event.ActionEvent;

/**
 * Controller of Menu View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class MenuController extends Controller{


    /**
     * @param actionEvent
     * Switch to feed view
     */
    public void OpenFeedView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Feed);
    }

    /**
     * @param actionEvent
     *
     * Switch to add view
     */
    public void OpenAddView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Add);
    }

    @Override
    public void setFeed(Feed feed) {
        super.setFeed(feed);
    }
}
