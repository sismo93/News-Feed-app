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

    public Router() {
        this.routes = new HashMap<>();

    }

    public static Router Instance(){

        if(instance == null){

            instance = new Router();

            instance.setRoot((AnchorPane) instance.loadFxml("RootView.fxml"));

        }

        return instance;
    }

    public void setRoot(AnchorPane root) {
        rootView = root;
    }

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


    public void changeView(Views view) {

        getRoot().getChildren().remove(currentView);

        if(view == Views.Menu){
            currentView = Router.Instance().loadFxml("MenuView.fxml");
        }
        else if(view == Views.Add){
            currentView = Router.Instance().loadFxml("AddView.fxml");

        }
        else if(view == Views.Feed){
            currentView = Router.Instance().loadFxml("FeedView.fxml");

        }
        else if(view == Views.Register){
            currentView = Router.Instance().loadFxml("Register.fxml");

        }
        getRoot().getChildren().add(currentView);


    }

    public AnchorPane getRoot() {
        return  rootView;
    }

    public enum  Views{
        Menu,
        Add,
        Feed,
        Register
    }
}
