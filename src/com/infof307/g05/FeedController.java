package com.infof307.g05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Controller of the Feed View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class FeedController {

    /**
     * ArticleViewField displays the content on the page
     */
    public ListView ArticleViewField;

    /**
     * Called after scene loading
     *
     * Init GUI
     * - fetch the content
     * - display the content
     */
    @FXML
    public void initialize(){

        // TODO: 3/5/19 get the news thru the parser module
        /// Dummy list
        ObservableList<String> articleList =
                FXCollections.observableArrayList(
                        "Article 1, Dz ioaj dizajoi djazoi djozai jdi za",
                        "Article 2, Doza jiodjza djazio jdoza jodza joida jz ",
                        "Article 3, Tizj iijdjzaij dzai jdazij doa zdo zajod jzaoijd"
                );

        ArticleViewField.setItems(articleList);

    }


    /**
     * @param actionEvent
     * Back to menu
     */
    public void OpenMenuView(ActionEvent actionEvent) {

    }
}
