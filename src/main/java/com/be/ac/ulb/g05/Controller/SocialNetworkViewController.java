package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.Controller.Router.*;

/**
 * Facebook & Twitter login view
 * @author iyamani
 * @codereview borsalinoK
 */
public class SocialNetworkViewController  extends AbstractController {

    public void OpenFacebookView() {
        Router.Instance().changeView(Views.Facebook);
    }

    public void OpenTwitterView() {
        Router.Instance().changeView(Views.TwitterAuth);

    }
}
