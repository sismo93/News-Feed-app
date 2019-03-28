package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.event.ActionEvent;
import com.be.ac.ulb.g05.Controller.Router.*;

/**
 * Controller of Menu View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class MenuController extends Controller{


    /**
     * @param actionEvent
     * Switch to feed view
     */
    public void OpenFeedView(ActionEvent actionEvent) {
        Router.Instance().changeView(Views.Feed);
    }

    /**
     * @param actionEvent
     *
     * Switch to add view
     */
    public void OpenAddView(ActionEvent actionEvent) {
        Router.Instance().changeView(Views.Add);
    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

}
