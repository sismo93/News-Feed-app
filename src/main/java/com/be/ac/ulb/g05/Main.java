package com.be.ac.ulb.g05;

import com.be.ac.ulb.g05.Controller.*;
import com.be.ac.ulb.g05.Model.Feed;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.be.ac.ulb.g05.Controller.Router.Instance;


public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {
        // Scene assignment
        final Scene scene = new Scene( Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize Feed Service
        Feed feed = new Feed();

        // Inject Dependency
        Instance().setFeed(feed);

        // route to menu
        Instance().changeView(Router.Views.Login);


    }

    public static void main(String[] args) {
        launch(args);
    }
}

