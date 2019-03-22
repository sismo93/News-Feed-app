package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.HashMap;

public class RootController extends Controller {

    @FXML
    public AnchorPane topPane;
    private BorderPane root;
    private Node currentView;
    private static RootController instance;
    private HashMap<String, Node> routes;
    private ArticleService feed;

    /**
     * Constructor
     */
    public RootController() {
        this.routes = new HashMap<>();
    }

    /**
     * Constructor
     *
     * @return instance of the Router
     */
    public static RootController Instance() {
        if (instance == null) {
            //
            instance = new RootController();
            //
            instance.setRoot((BorderPane) instance.loadFxml("RootView.fxml"));
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
     * @return XML view
     */
    private Node loadFxml(String fxml) {
        Node view = null;
        if (routes.containsKey(fxml)) return routes.get(fxml);

        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml));


        try {
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (view != null && !fxml.equals("RootView.fxml")) {

            Controller controller = loader.getController();
            controller.setArticleService(feed);
            controller.setupView();
        }

        routes.put(fxml, view);

        return view;
    }

    public void setFeed(ArticleService feed) {
        this.feed = feed;
    }


    /**
     * Changes XML view
     *
     * @param view window view
     */
    public void changeView(Views view) {

        getRoot().setCenter(currentView);

        if (view == Views.Menu) {
            currentView = RootController.Instance().loadFxml("MenuView.fxml");
        } else if (view == Views.Add) {
            currentView = RootController.Instance().loadFxml("AddView.fxml");
        } else if (view == Views.Login) {
            currentView = RootController.Instance().loadFxml("LoginFxml.fxml");
        } else if (view == Views.Feed) {
            currentView = RootController.Instance().loadFxml("FeedView.fxml");
        } else if (view == Views.Register) {
            currentView = RootController.Instance().loadFxml("RegisterFxml.fxml");
        } else if(view == Views.Article){
            currentView = RootController.Instance().loadFxml("ArticleView.fxml");
        }else if(view == Views.Preview){
            currentView = RootController.Instance().loadFxml("ArticlePreview.fxml");
        }

        if (view == Views.Login) {
            setTopPaneVisible(false);
        } else if (view == Views.Menu) {
            setTopPaneVisible(true);
        }

        getRoot().setCenter(currentView);

    }

    /**
     * Gets the root window
     *
     * @return the root window
     */
    public BorderPane getRoot() {
        return root;
    }

    /**
     * Enum of different views
     */
    public enum Views {
        Menu,
        Add,
        Feed,
        Register,
        Root,
        Article, Preview, Login
    }

    public void OpenMenuView(ActionEvent actionEvent) {
        RootController.Instance().changeView(Views.Menu);
    }

    /**
     * @param actionEvent Switch view to Menu
     *                    Mask top Pane
     */
    public void OnDisconnect(ActionEvent actionEvent) {
        RootController.Instance().changeView(Views.Login);
    }

    /**
     * @param shouldBeVisible Set visibility of top Pane
     */
    public void setTopPaneVisible(boolean shouldBeVisible) {

        if (!shouldBeVisible) {
            root.getTop().setStyle("-fx-opacity: 0;");
            root.getTop().setDisable(true);
        } else {
            root.getTop().setStyle("-fx-opacity: 1;");
            root.getTop().setDisable(false);

        }
    }
}
