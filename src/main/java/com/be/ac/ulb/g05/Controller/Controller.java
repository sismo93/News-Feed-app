package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Feed;

public abstract class Controller  {

    protected Feed feed;

    public void setFeed(Feed feed){
        this.feed = feed;
    }


}
