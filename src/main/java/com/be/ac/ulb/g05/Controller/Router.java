package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
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
    private ArticleService feed;
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
            Router.instance.setRoot((AnchorPane) instance.loadFxml("RootView.fxml", null));

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
    private Node loadFxml(String fxml, Article article) {
        Node view = null;
        if(routes.containsKey(fxml)) return routes.get(fxml);

        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml));

        System.out.println(loader.getLocation());

        try {
            view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(view != null && !fxml.equals("RootView.fxml")){

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
     * @param view window view
     * @param article
     */
    public void changeView(Views view, Article article) {

        getRoot().getChildren().remove(currentView);

        if(view == Views.Menu){
            currentView = Router.Instance().loadFxml("MenuView.fxml", null);
        }
        else if(view == Views.Add){
            currentView = Router.Instance().loadFxml("AddView.fxml", null);
        }
        else if(view == Views.Login) {
            currentView = Router.Instance().loadFxml("LoginFxml.fxml", null);
        }
        else if(view == Views.Feed){
            currentView = Router.Instance().loadFxml("FeedView.fxml", null);
        }
        else if(view == Views.Register){
            currentView = Router.Instance().loadFxml("RegisterFxml.fxml", null);
        }
        else if(view == Views.Preview) {
            currentView = Router.Instance().loadFxml("ArticlePreview.fxml", null);
        }
        else if(view == Views.Article) {
            currentView = Router.Instance().loadFxml("ArticleView.fxml", null);
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
        Root,
        Login,
        Preview, Article,
    }
}
