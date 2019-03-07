package com.infof307.g05;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;


public class LoginController {


    public PasswordField passwordField;
    public TextField userNameField;
    private Users user;
    private DAO<Users> usersDAO;

    private Alert alert;



    public LoginController(){
        user = new Users();
        usersDAO = new UsersDAO();


    }

    @FXML
    private void LoginButton(){
        user.setLogin(userNameField.getText());
        user.setPassword(passwordField.getText());

        if (usersDAO.checkLoginAndPassword(user)){
            // Lancer Scene ADD / FEED

        }
        else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText(" username or password not matching");
            alert.showAndWait();

        }

    }

    @FXML
    private void RegisterButton(){
        RegisterReglement();


    }



    public void RegisterReglement(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reglement");
        alert.setHeaderText("Do you accept terms and conditions");
        alert.setContentText("Regulation to be established with the client");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // GO TO REGISTER FXML
        }
    }
}
