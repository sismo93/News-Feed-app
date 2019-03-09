package com.infof307.g05;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;

public class Router {
    private AnchorPane rootView;
    private Node currentView;
    private static Router instance;
    private HashMap<String, Node> routes;

    /**
     * Constructor
     */
    public Router() {
        this.routes = new HashMap<>();
    }

    /**
     * Constructor
     * @return instance of the Router
     */
    public static Router Instance(){
        if(instance == null){
            instance = new Router();
            instance.setRoot((AnchorPane) instance.loadFxml("RootView.fxml"));
        }
        return instance;
    }

    /**
     * Sets up the AnchorPane
     * @param root AnchorPane
     */
    public void setRoot(AnchorPane root) {
        rootView = root;
    }

    /**
     * Loads XML windows
     * @param fxml XML node
     * @return XML view
     */
    private Node loadFxml(String fxml) {
        Node view = null;
        if(routes.containsKey(fxml)) return routes.get(fxml);

        FXMLLoader loader = new FXMLLoader(Router.this.getClass().getResource(fxml));
        try {
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        routes.put(fxml, view);
        return view;
    }


    /**
     * Changes XML view
     * @param view window view
     */
    public void changeView(Views view) {
        getRoot().getChildren().remove(currentView);

        if(view == Views.Menu){
            currentView = Router.Instance().loadFxml("MenuView.fxml");
        }
        else if(view == Views.Add){
            currentView = Router.Instance().loadFxml("AddView.fxml");

        }
        else if(view == Views.Login) {
            currentView = Router.Instance().loadFxml("LoginFxml.fxml");
        }
        else if(view == Views.Feed){
            currentView = Router.Instance().loadFxml("FeedView.fxml");

        }
        else if(view == Views.Register){
            currentView = Router.Instance().loadFxml("RegisterFxml.fxml");

        }
        getRoot().getChildren().add(currentView);


    }

    /**
     * Gets the root window
     * @return the root window
     */
    public AnchorPane getRoot() {
        return  rootView;
    }

    /**
     * Enum of different views
     */
    public enum  Views{
        Menu,
        Add,
        Feed,
        Register,
        Login
    }
}
