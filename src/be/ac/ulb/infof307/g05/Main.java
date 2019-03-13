package be.ac.ulb.infof307.g05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static be.ac.ulb.infof307.g05.Router.*;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) {
        // Scene assignment
        final Scene scene = new Scene( Instance().getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        // route to menu
        Instance().changeView(Views.Add);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

