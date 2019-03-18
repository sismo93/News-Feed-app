package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

/**
 * Controller of the Feed View
 *
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class FeedController {

    /**
     * ArticleViewField displays the content on the page
     */
    public VBox articleContainer;

    /**
     * Called after scene loading
     * <p>
     * Init GUI
     * -
     * - fetch the content
     * - display the title of each article
     * -
     */



    @FXML
    public void initialize() {
        ObservableList<String> names = FXCollections.observableArrayList();
        ArticleData.Instance().getArticleData().forEach(strings -> {
            names.add(strings[0]+"\n\n"+strings[1].substring(0,60));

        });
        ListView<String> listView = new ListView<String>(names);
        listView.setFixedCellSize(100);
        articleContainer.getChildren().add(listView);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
                //Action pour ouvrir la selection
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, newvalue, ButtonType.OK);
                alert.showAndWait();
            }});

        ArticleData.Instance()
                .getArticleData()
                    .addListener((ListChangeListener<String[]>)
                        change -> {
                            if (change.next()) {

                                // remove articles from scene
                                articleContainer.getChildren().clear();
                                // add article back including updated articles
                                ObservableList<? extends String[]> updatedList = change.getList();
                                ObservableList<String> updatednames = FXCollections.observableArrayList();
                                for (String[] updatedArticle : updatedList) {
                                    //Action pour ouvrir la selection
                                    updatednames.add(updatedArticle[0]+"\n\n"+updatedArticle[1].substring(0,60));
                                }
                                ListView<String> updatedlistView = new ListView<String>(updatednames);
                                updatedlistView.setFixedCellSize(100);
                                articleContainer.getChildren().add(updatedlistView);
                            }


                        });

//        ArticleData.Instance().getArticleData().forEach(strings -> {

//            TextFlow flow = new TextFlow();

//            Text text1 = new Text(strings[0] + " ");
//            Text text2 = new Text(strings[1]);


            // text format with custom style

//            text1.setStyle("-fx-font-weight: bold");


            // fill scene with new component

//            flow.getChildren().addAll(text1, text2);

//            articleContainer.getChildren().add(flow);
//        });


        // fetching data

//        ArticleData.Instance()
//                .getArticleData()
//                    .addListener((ListChangeListener<String[]>)
//                        change -> {
//                            if (change.next()) {
//
//                                // remove articles from scene
//                                articleContainer.getChildren().clear();
//                                // add article back including updated articles
//                                ObservableList<? extends String[]> updatedList = change.getList();
//
//                                for (String[] updatedArticle : updatedList) {
//                                    // Build scene component
//
//                                    TextFlow flow = new TextFlow();
//
//                                    Text text1 = new Text(updatedArticle[0] + " ");
//                                    Text text2 = new Text(updatedArticle[1]);
//
//
//                                    // text format with custom style
//
//                                    text1.setStyle("-fx-font-weight: bold");
//
//
//                                    // fill scene with new component
//
//                                    flow.getChildren().addAll(text1, text2);
//
//                                    articleContainer.getChildren().add(flow);
//
//
//                                }
//                            }
//
//
//                        });
    }

    /**
     * @param actionEvent Back to menu
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }
}
