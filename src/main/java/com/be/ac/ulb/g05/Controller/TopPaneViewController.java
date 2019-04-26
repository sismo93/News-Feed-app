package com.be.ac.ulb.g05.Controller;


/**
 * TopPaneView controller
 * extends AbstractController
 */
public class TopPaneViewController extends AbstractController {

    /**
     * Opens menu view
     */
    public void OpenMenuView() {
        Router.Instance().changeView(Router.Views.Menu);
    }

    /**
     */
    public void OnDisconnect() {
        articleService.reset();
        Router.Instance().changeView(Router.Views.Login);
    }

    public void OpenFeedView() {
        Router.Instance().changeView(Router.Views.Feed);
    }
}
