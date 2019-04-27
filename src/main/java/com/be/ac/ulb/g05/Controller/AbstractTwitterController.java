package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.TwitterService;

public abstract class AbstractTwitterController extends AbstractController {

    protected TwitterService twitterService;

    @Override
    public void injectDependencies(DependencyInjector dependencyInjector) {
        super.injectDependencies(dependencyInjector);
        twitterService = dependencyInjector.getTwitterService();
    }
}
