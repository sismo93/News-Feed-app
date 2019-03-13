package com.be.ac.ulb.g05;

import com.be.ac.ulb.g05.Controller.*;
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
        // route to menu
        Instance().changeView(Router.Views.Login);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

