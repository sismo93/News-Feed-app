package com.be.ac.ulb.g05.Controller;


public class AddArticleMenuController  extends  AbstractController{

    public void onAddByMapPressed( ) {

        Router.Instance().changeView(Router.Views.AddWithMap);
    }

    public void onAddByWebSitePressed( ) {
        Router.Instance().changeView(Router.Views.AddFromWebSite);
    }
}
