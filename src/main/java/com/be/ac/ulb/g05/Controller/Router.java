package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.HashMap;

public class Router {

    @FXML
    private BorderPane root;
    private Node currentView;
    private static Router instance;
    private HashMap<String, Node> routes;
    private ArticleService articleService;

    /**
     * Enum of different views
     */
    public enum Views{
        Add ("AddView.fxml"),
        Article ("ArticleView.fxml"),
        Feed ("FeedView.fxml"),
        Login ("LoginFxml.fxml"),
        Menu ("MenuView.fxml"),
        Preview ("ArticlePreview.fxml"),
        Register ("RegisterFxml.fxml"),
        Root ("RootView.fxml"),
        TopPane ("TopPaneView.fxml"),
        Media("MediaView.fxml");

        private String value;

        Views(String s) {
            value = s;
        }

        @Override
        public String toString() {
            return value;
        }

    }


    /**
     * Constructor
     */
    public Router() {
        this.routes = new HashMap<>();
    }


    public static Router Instance() {
        if (instance == null) {
            instance = new Router();
        }
        return instance;
    }

    /**
     * Sets up the AnchorPane
     *
     * @param root AnchorPane
     */
    public void setRoot(BorderPane root) {
        this.root = root;
    }


    /**
     * Loads XML windows
     *
     * @param fxml XML node
     * @return XML view or null if the view could not be loaded
     */
    private Node loadFxml(String fxml) {
        Node view = null;
        if (routes.containsKey(fxml)) return routes.get(fxml);


        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml));



        try {

            view = loader.load();

            Controller controller = loader.getController();
            controller.setArticleService(articleService);
            controller.setupView();



            routes.put(fxml, view);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "GUI could not be loaded", ButtonType.OK);
            alert.showAndWait();
        }

        return view;
    }

    public void setFeed(ArticleService feed) {
        this.articleService = feed;
    }


    /**
     * Changes XML view
     *
     * @param view window view
     */
    public void changeView(Views view) {

        currentView = loadFxml(view.value);

        if (view == Views.Login) {
            getRoot().setTop(null);
        } else {
            getRoot().setTop(loadFxml(Views.TopPane.value));
        }

        getRoot().setCenter(currentView);

    }


    /**
     * Gets the root window
     *
     * @return the root window
     */
    public BorderPane getRoot() {
        if (root == null) {
            setRoot((BorderPane) loadFxml(Views.Root.value));
        }
        return root;
    }
}
