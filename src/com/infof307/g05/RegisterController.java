package com.infof307.g05;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class RegisterController {


    public TextField userName;
    public PasswordField password;
    public TextField email;

    private Users user;
    private DAO<Users> usersDAO;
    private Alert alert;


    public RegisterController(){
        user = new Users();
        usersDAO = new UsersDAO();


    }

    @FXML
    private void RegisterButton(){

        user.setLogin(userName.getText());
        user.setPassword(password.getText());
        user.setMail(email.getText());

        if (!usersDAO.loginExist(user) && !usersDAO.mailExist(user)){

            usersDAO.add(user); // Add in database
            Router.Instance().changeView(Router.Views.Menu); // Go to menu



        }
        else{

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("This account already exist");
            alert.showAndWait();

        }

    }


}
