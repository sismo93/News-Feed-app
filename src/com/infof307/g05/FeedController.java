package com.infof307.g05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Arrays;

import static com.infof307.g05.URLReader.Article;
import static com.infof307.g05.URLReader.Homepage;

/**
 * Controller of the Feed View
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
     *
     * Init GUI
     * - fetch the content
     * - display the content
     */
    @FXML
    public void initialize(){


        // fetching data
        ArrayList<String> urlsList = null;

        try {
            urlsList =  Homepage("https://www.bbc.com/", "news", 4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // retrieving content

        String[] article = new String[2];
        String url;

        for (String s : urlsList) {

            url = s;

            // fetch the correponding article content from the url

            try {
                article = Article(url).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }


            // Build scene component
            TextFlow flow = new TextFlow();

            Text text1=new Text(article[0] + " ");
            Text text2=new Text(article[1]);


            // text format with custom style
            text1.setStyle("-fx-font-weight: bold");


            // fill scene with new component
            flow.getChildren().addAll(text1, text2);

            articleContainer.getChildren().add(flow);

        }
    }


    /**
     * @param actionEvent
     * Back to menu
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

}
