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
     * Logs off the user from application
     */
    public void OnDisconnect() {
        articleService.reset();
        twitterService.reset();
        Router.Instance().changeView(Router.Views.Login);
    }

    /**
     * Opens Feed view
     */
    public void OpenFeedView() {
        Router.Instance().changeView(Router.Views.Feed);
    }

    /**
     * Opens Help view
     */
    public void OpenHelpView() {
        Router.Instance().changeView(Router.Views.Help);
    }
}
