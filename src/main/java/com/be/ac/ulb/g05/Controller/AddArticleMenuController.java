package com.be.ac.ulb.g05.Controller;


/**
 * Controller of the AddArticleView, will only open the right view
 */
public class AddArticleMenuController  extends  AbstractController{

    public void onAddByMapPressed( ) {

        Router.Instance().changeView(Router.Views.AddWithMap);
    }

    public void onAddByWebSitePressed( ) {
        Router.Instance().changeView(Router.Views.AddFromWebSite);
    }
}
