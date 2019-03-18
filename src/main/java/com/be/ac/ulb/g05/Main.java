package com.be.ac.ulb.g05;

import com.be.ac.ulb.g05.Controller.*;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.be.ac.ulb.g05.Controller.Router.Instance;


public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {

        // Initialize ArticleService Service
        ArticleService feed = new ArticleService();

        // Inject Dependency
        Instance().setFeed(feed);


        // Scene assignment
        final Scene scene = new Scene( Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();

        Instance().changeView(Router.Views.Add);




    }

    public static void main(String[] args) {
        launch(args);
    }
}

