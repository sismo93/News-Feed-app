package com.infof307.g05;

import javafx.event.ActionEvent;

/**
 * Controller of Menu View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class MenuController {


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
}
