package com.be.ac.ulb.g05;

import com.be.ac.ulb.g05.Controller.DependencyInjector;
import com.be.ac.ulb.g05.Controller.Router;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.be.ac.ulb.g05.Controller.Router.Views;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {

        // Initialize ArticleService Service
        DependencyInjector dependencyInjector = new DependencyInjector();
        // Inject Dependency
        Router.Instance().setDependencyInjector(dependencyInjector);

        // Scene assignment
        final Scene scene = new Scene(Router.Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();

        Router.Instance().changeView(Views.Login);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

