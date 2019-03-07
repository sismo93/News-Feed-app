package com.infof307.g05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Controller of the Add View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class AddController {

    /**
     * Controls elements displayed on screen
     */
    @FXML
    public TextField UrlBox;
    @FXML
    public ChoiceBox ArticleNumberBox;
    @FXML
    public ChoiceBox CategoryBox;
    @FXML
    public Button AddButton;


    /**
     * Called after scene loading
     *
     * Initialize the various controls on the screen
     */
    @FXML
    public void initialize() {
        ObservableList<String> categoryList =
                FXCollections.observableArrayList(
                        "Sport",
                        "Politique",
                        "Culture"
                );


        ObservableList<Integer> articleNumberList =
                FXCollections.observableArrayList(
                        1,
                        2,
                        3
                );

        ArticleNumberBox.setItems(articleNumberList);

        CategoryBox.setItems(categoryList);

    }


    /**
     * @param actionEvent
     * Add the article to the feed
     */
    public void OnButtonPressed(ActionEvent actionEvent) {
        /// link
    }


    /**
     * @param actionEvent
     * Open the Menu View
     */
    public void OpenMenuView(ActionEvent actionEvent) {

    }
}
