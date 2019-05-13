package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.TwitterService;

/**
 * @author @borsalinoK
 * @codereview @mouscb
 * Allow class to communicate with TwitterService
 */
public abstract class AbstractTwitterController extends AbstractController {

    /**
     * Twitter service
     */
    protected TwitterService twitterService;

    /**
     * @param dependencyInjector object responsible for delivering the dependency
     * called on initialization
     */
    @Override
    public void injectDependencies(DependencyInjector dependencyInjector) {
        super.injectDependencies(dependencyInjector);
        twitterService = dependencyInjector.getTwitterService();
    }
}
