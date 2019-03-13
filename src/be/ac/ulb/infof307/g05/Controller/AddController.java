package be.ac.ulb.infof307.g05.Controller;


import be.ac.ulb.infof307.g05.Model.ArticleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.Optional;

import static be.ac.ulb.infof307.g05.URLReader.Article;
import static be.ac.ulb.infof307.g05.URLReader.Homepage;


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
                    article.add(Article(url).get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(article.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Importation failed", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        for (int i = 0; i < article.size(); i++) {
            //Popup dialog window
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    article.get(i)[1].substring(0, 241) + " ... ",
                    ButtonType.OK, ButtonType.CANCEL);
            //Setup dialog window controls
            ButtonType importButton = new ButtonType("Import");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(importButton, cancelButton);
            alert.setTitle("Article Preview ");
            alert.setHeaderText(article.get(i)[0]);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == importButton){
                // ... user chose OK
                ArticleData.Instance().addArticle(article.get(i));

            } else if(result.get() == cancelButton){
                // ... user chose CANCEL or closed the dialog
            }
        }

    }


    /**
     * @param actionEvent
     * Open the Menu View
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

}
