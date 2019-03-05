package com.infof307.g05;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicalUserInterface extends Application {

    /**
     * Defines the email regex for validity checks
     */
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    /**
     * Starting window for the GUI
     * @param primaryStage GUI window
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FeedBuzz");
        GridPane gridPane = rootPane();
        addWelcomeUI(gridPane, primaryStage);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Defines the base window
     * @return GridPane
     */
    private GridPane rootPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    /**
     * Defines the window for login/register forms
     * @return GridPane
     */
    private GridPane formsPane() {
        GridPane gridPane = rootPane();
        ColumnConstraints columnOneConstraints = new ColumnConstraints(50, 50, Double.MAX_VALUE);
        columnOneConstraints.setHgrow(Priority.ALWAYS);
        ColumnConstraints columnTwoConstraints = new ColumnConstraints(500, 500, Double.MAX_VALUE);
        columnTwoConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstraints);
        return gridPane;
    }

    /**
     * Adds all fields for registration form
     * @param gridPane the window layout
     * @param stage the window itself
     */
    @SuppressWarnings("Duplicates")
    private void addRegistrationUI(GridPane gridPane, Stage stage) {
        Label headerLabel = new Label("Registration Form");
        headerSetup(gridPane, headerLabel);

        Label nameLabel = new Label("Username: ");
        gridPane.add(nameLabel, 0, 1);

        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        usernameField.setPromptText("More than 4 characters...");
        gridPane.add(usernameField, 1, 1);

        Label emailLabel = new Label("E-mail: ");
        gridPane.add(emailLabel, 0, 2);

        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);

        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setPromptText("More than 8 characters with numbers...");
        gridPane.add(passwordField, 1 , 3);

        Button submitButton = submitButton(gridPane);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Username can't be empty");
                    return;
                }

                if (usernameField.getText().length() < 4) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Username must have more than 4 characters");
                    return;
                }

                if (emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Email can't be empty");
                    return;
                }

                if (!isEmailValid(emailField.getText())) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Email is not valid");
                    return;
                }

                if (passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Password can't be empty");
                    return;
                }

                if (passwordField.getText().length() < 8) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Password must have more than 8 characters");
                    return;
                }

                if (passwordField.getText().matches(".*\\d+.*")) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Password must contain numbers");
                    return;
                }

                // TODO
                // Here are stored the user details
                User user = new User(usernameField.getText(), passwordField.getText(), emailField.getText());

                welcomePaneSetup(stage);
            }
        });
    }

    /**
     * Adds all fields for login form
     * @param gridPane the window layout
     * @param stage the window itself
     */
    @SuppressWarnings("Duplicates")
    private void addLoginUI(GridPane gridPane, Stage stage) {
        Label headerLabel = new Label("Login Form");
        headerSetup(gridPane, headerLabel);

        Label nameLabel = new Label("Username: ");
        gridPane.add(nameLabel, 0, 1);

        TextField usernameField = new TextField();
        usernameField.setPrefHeight(40);
        gridPane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1 , 2);

        Button submitButton = submitButton(gridPane);

        // TODO
        // Do checks with the database 
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Username can't be empty");
                    return;
                }

                if (passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Error", "Password can't be empty");
                    return;
                }

                welcomePaneSetup(stage);
            }
        });
    }

    /**
     * Defines the user control panel UI
     * @param gridPane the control panel window
     */
    private void addControlPanelUI(GridPane gridPane) {
        Label headerLabel = new Label("Welcome to FeedBuzz 7bibi!");
        headerSetup(gridPane, headerLabel);
    }

    /**
     * Defines the welcome UI
     * @param gridPane the welcome layout
     * @param stage the welcome window
     */
    private void addWelcomeUI(GridPane gridPane, Stage stage) {
        Label headerLabel = new Label("Welcome to FeedBuzz!");
        headerSetup(gridPane, headerLabel);

        Button loginButton = new Button("Log In");
        Button registerButton = new Button("Register");

        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);

        registerButton.setPrefHeight(40);
        registerButton.setDefaultButton(false);
        registerButton.setPrefWidth(100);

        gridPane.add(loginButton, 0, 1, 2, 1);
        gridPane.add(registerButton, 0, 1, 2, 1);
        GridPane.setHalignment(loginButton, HPos.LEFT);
        GridPane.setHalignment(registerButton, HPos.RIGHT);
        GridPane.setMargin(loginButton, new Insets(20, 0, 20, 0));
        GridPane.setMargin(registerButton, new Insets(20, 0, 20, 0));

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane formPane = formsPane();
                addRegistrationUI(formPane, stage);
                Scene registrationScene = new Scene(formPane, 800, 500);
                stage.setScene(registrationScene);
                stage.show();
            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GridPane formPane = formsPane();
                addLoginUI(formPane, stage);
                Scene loginScene = new Scene(formPane, 800, 500);
                stage.setScene(loginScene);
                stage.show();
            }
        });
    }

    /**
     * Sets up the layout and scene of the welcome pane
     * @param stage the window itself
     */
    private void welcomePaneSetup(Stage stage) {
        GridPane welcomePane = rootPane();
        addControlPanelUI(welcomePane);
        Scene welcomeScene = new Scene(welcomePane, 800, 500);
        stage.setScene(welcomeScene);
        stage.show();
    }

    /**
     * Sets up the header for every applicable window
     * @param gridPane the parent window layout
     * @param headerLabel the title
     */
    private void headerSetup(GridPane gridPane, Label headerLabel) {
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
    }

    /**
     * Shows the error alert with custom message
     * @param alertType type of alert
     * @param owner parent window
     * @param title title of the alert
     * @param message message of the alert
     */
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    /**
     * Checks whether the email has a valid format or not
     * @param email user's email
     * @return true if valid, false if not valid
     */
    private boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Sets up the submit button layout
     * @param gridPane the window that contains the button
     * @return the submit button
     */
    private Button submitButton(GridPane gridPane) {
        Button button = new Button("Submit");
        button.setPrefHeight(40);
        button.setDefaultButton(true);
        button.setPrefWidth(100);
        gridPane.add(button, 0, 4, 2, 1);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setMargin(button, new Insets(20, 0, 20, 0));

        return button;
    }
}
