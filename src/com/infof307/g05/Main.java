package com.infof307.g05;

import javafx.application.Application;
<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginFxml.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
=======
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {
        // Scene assignment
        final Scene scene = new Scene( Router.Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        // route to menu
        Router.Instance().changeView(Router.Views.Menu);
>>>>>>> h4t3
    }

    public static void main(String[] args) {
        launch(args);
    }
<<<<<<< HEAD
}
=======

}
>>>>>>> h4t3
