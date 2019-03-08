package com.infof307.g05;

import com.infof307.g05.Router;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {
        // Scene assignment
        final Scene scene = new Scene( Router.Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        // route to menu
        Router.Instance().changeView(Router.Views.Login);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

