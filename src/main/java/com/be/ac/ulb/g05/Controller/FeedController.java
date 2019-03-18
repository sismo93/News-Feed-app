package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Controller of the ArticleService View
 *
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class FeedController extends Controller {

    /**
     * ArticleViewField displays the content on the page
     */
    public VBox articleContainer;

    /**
     * Called after scene loading
     * <p>
     * Init GUI
     * - fetch the content
     * - display the content
     */
    @FXML
    public void initialize() {

        /*
        ArticleData.Instance().getArticleData().forEach(strings -> {

            TextFlow flow = new TextFlow();

            Text text1 = new Text(strings[0] + " ");
            Text text2 = new Text(strings[1]);


            // text format with custom style

            text1.setStyle("-fx-font-weight: bold");


            // fill scene with new component

            flow.getChildren().addAll(text1, text2);

            articleContainer.getChildren().add(flow);
        });


        // fetching data

        ArticleData.Instance()
                .getArticleData()
                    .addListener((ListChangeListener<String[]>)
                        change -> {
                            if (change.next()) {

                                // remove articles from scene
                                articleContainer.getChildren().clear();
                                // add article back including updated articles
                                ObservableList<? extends String[]> updatedList = change.getList();

                                for (String[] updatedArticle : updatedList) {
                                    // Build scene component

                                    TextFlow flow = new TextFlow();

                                    Text text1 = new Text(updatedArticle[0] + " ");
                                    Text text2 = new Text(updatedArticle[1]);


                                    // text format with custom style

                                    text1.setStyle("-fx-font-weight: bold");


                                    // fill scene with new component

                                    flow.getChildren().addAll(text1, text2);

                                    articleContainer.getChildren().add(flow);


                                }
                            }


                        });

                        */
    }

    /**
     * @param actionEvent Back to menu
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

    @Override
    public void setFeed(ArticleService feed) {
        super.setFeed(feed);
    }
}
