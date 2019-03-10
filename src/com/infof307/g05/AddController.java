package com.infof307.g05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Optional;

import static com.infof307.g05.URLReader.Article;
import static com.infof307.g05.URLReader.Homepage;

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
                        "news",
                        "sport",
                        "culture"
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

        String url = UrlBox.getText();

        if (url.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please enter a valid Url", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        ArrayList<String[]> article = new ArrayList<>();

        if(CategoryBox.getSelectionModel().isEmpty() || ArticleNumberBox.getSelectionModel().isEmpty()){
            try {

                article = Article(url);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {

            ArrayList<String> urlsList =  Homepage("https://www.bbc.com/",
                    CategoryBox.getSelectionModel().getSelectedItem().toString(),
                    (int) ArticleNumberBox.getSelectionModel().getSelectedItem());

            for (String s : urlsList) {

                url = s;

                // fetch the correponding article content from the url

                try {
                    article = Article(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                article.get(0)[0],
                ButtonType.OK, ButtonType.CANCEL);

        alert.setTitle("Article Importation ");
        alert.setHeaderText("Voulez importer l'article");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            // ... user chose OK
            ArticleData.Instance().addArticle(article.get(0));

        } else if(result.get() ==ButtonType.CANCEL){
            // ... user chose CANCEL or closed the dialog
        }

    }


    /**
     * @param actionEvent
     * Open the Menu View
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

    /**
     * Shows the error alert with custom message
     * @param alertType type of alert
     * @param owner parent window
     * @param title title of the alert
     * @param message message of the alert
     */
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
