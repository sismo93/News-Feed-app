package com.be.ac.ulb.g05.Controller;

import javafx.event.ActionEvent;

public class TopPaneViewController extends Controller{

    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

    /**
     * @param actionEvent Switch view to Menu
     *                    Mask top Pane
     */
    public void OnDisconnect(ActionEvent actionEvent) {

        articleService.reset();
        Router.Instance().changeView(Router.Views.Login);
    }

}
