package com.be.ac.ulb.g05.Controller;


/**
 * Controller of the AddArticleView, will only open the right view
 */
public class AddArticleMenuController  extends  AbstractController{

    /**
     * Changes the view to map
     */
    public void onAddByMapPressed( ) {
        Router.Instance().changeView(Router.Views.AddWithMap);
    }

    /**
     * Changes the view to website add
     */
    public void onAddByWebSitePressed( ) {
        Router.Instance().changeView(Router.Views.AddFromWebSite);
    }
}
