package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.Controller.Router.*;

/**
 * Controller of Menu View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class MenuController extends Controller{


    /**
     * Opens the feed
     */
    public void OpenFeedView() {
        Router.Instance().changeView(Views.Feed);
    }

    /**
     * Opens the add view
     */
    public void OpenAddView() {
        Router.Instance().changeView(Views.Add);
    }

    /**
     * Sets the article service
     * @param articleService article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

}
