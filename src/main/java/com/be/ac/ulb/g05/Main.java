package com.be.ac.ulb.g05;

import com.be.ac.ulb.g05.Controller.RootController;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {

        // Initialize ArticleService Service
        ArticleService feed = new ArticleService();
        // Inject Dependency
        RootController.Instance().setFeed(feed);

        // Scene assignment
        final Scene scene = new Scene( RootController.Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();

        RootController.Instance().changeView(RootController.Views.Add);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

