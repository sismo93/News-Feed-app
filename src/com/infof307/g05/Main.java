package com.infof307.g05;

import javafx.application.Application;
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
    }

    public static void main(String[] args) {
        launch(args);
    }

/***
    public static void main(String[] args)

    {
        Users user;
        user = new Users("admin","admin","admin");
        DAO<Users> UsersDAO = new UsersDAO();
        //boolean bol = UsersDAO.findLogin(user);
        //UsersDAO.add(user);
        UsersDAO.checkLoginAndPassword(user);
       System.out.println(UsersDAO.checkLoginAndPassword(user));




    }***/

}
