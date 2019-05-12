package com.be.ac.ulb.g05.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.HashMap;

/**
 * Router
 *
 * transition between views
 *
 */
public class Router {

    @FXML
    private BorderPane root;

    /**
     * Views router
     */
    private static Router instance;

    /**
     * HashMap of nodes & their view
     */
    private HashMap<String, Route> routes;

    /**
     * Article service
     */
    private DependencyInjector dependencyInjector;

    /**
     * Enum of different views
     */
    public enum Views{
        AddWithMap ("AddView.fxml"),
        Article ("ArticleView.fxml"),
        Feed ("FeedView.fxml"),
        Login ("LoginFxml.fxml"),
        Menu ("MenuView.fxml"),
        Preview ("ArticlePreview.fxml"),
        Register ("RegisterFxml.fxml"),
        Root ("RootView.fxml"),
        TopPane ("TopPaneView.fxml"),
        Media("MediaView.fxml"),
        ChooseArticle("ChooseArticleView.fxml"),
        SocialNetwork("SocialNetworkView.fxml"),
        Twitter("TwitterView.fxml"),
        TwitterAuth("TwitterAuthView.fxml"),
        Facebook("FacebookView.fxml"),
        FacebookData("FacebookDataView.fxml"),
        AddFromWebSite("AddFromWebsiteView.fxml"),
        AddArticle("AddArticleMenuView.fxml"),
        Help("HelpView.fxml");

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


    /**
     * Router instance
     * @return instance
     */
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
    private void setRoot(BorderPane root) {
        this.root = root;
    }


    /**
     * Loads XML windows
     *
     * @param fxml XML node
     * @return XML view or null if the view could not be loaded
     */
    private Route loadFxml(String fxml) {
        Route route = null;

        if (routes.containsKey(fxml)) {
            route = routes.get(fxml);
            return route;
        }

        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml));

        try {
            Node root = loader.load();

            AbstractController controller = loader.getController();
            controller.injectDependencies(dependencyInjector);
            controller.setupView();

            route = new Route(root, controller);

            routes.put(fxml, route);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "GUI could not be loaded", ButtonType.OK);
            alert.showAndWait();
        }

        return route;
    }

    /**
     * Dependency injector
     * @param dependencyInjector object responsible for injection
     */
    public void setDependencyInjector(DependencyInjector dependencyInjector) {
        this.dependencyInjector = dependencyInjector;
    }

    /**
     * Changes XML view
     *
     * @param view window view
     */
    public void changeView(Views view) {

        Route route = loadFxml(view.value);
        AbstractController controller = route.getController();
        Node currentView = route.getRoot();

        if (view == Views.Login || view == Views.Register) {
            getRoot().setTop(null);
        } else {
            getRoot().setTop(loadFxml(Views.TopPane.value).getRoot());
        }

        getRoot().setCenter(currentView);

        controller.onActive();

    }


    /**
     * Gets the root window
     *
     * @return the root window
     */
    public BorderPane getRoot() {
        if (root == null) {
            Node root = loadFxml(Views.Root.value).getRoot();
            setRoot((BorderPane) root);
        }
        return root;
    }
}
