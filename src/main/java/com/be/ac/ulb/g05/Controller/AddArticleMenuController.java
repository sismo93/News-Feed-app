package com.be.ac.ulb.g05.Controller;

import javafx.event.ActionEvent;

public class AddArticleMenuController  extends  AbstractController{

    public void onAddArticlePressed(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Add);
    }

    public void onAddMapPressed(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.AddMapView);
    }
}
