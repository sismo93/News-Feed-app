package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.Controller.Router.*;


public class SocialNetworkViewController  extends Controller{


    public void OpenFacebookView() {
        Router.Instance().changeView(Views.Facebook);
    }

    public void OpenTwitterView() {
        Router.Instance().changeView(Views.Twitter);
    }
}
