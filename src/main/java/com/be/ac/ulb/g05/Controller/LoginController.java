package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Optional;
import com.be.ac.ulb.g05.Controller.Router.*;


/**
 * Controller of the Login
 * @author @MnrBn
 * @codereview @Mouscb
 */
public class LoginController extends Controller {

    /**
     * Password field
     */
    public PasswordField passwordField;

    /**
     * Username field
     */
    public TextField userNameField;

    /**
     * Users object
     */
    private Users user;

    /**
     * User DAO
     */
    private DAO<Users> usersDAO;

    /**
     * Constructor
     */
    public LoginController(){
        user = new Users();
        usersDAO = new UsersDAO();
    }

    /**
     * Defines the login button
     * Checks whether the login information matches the database
     * Otherwise, throw an alert error
     */
    @FXML
    private void OnLoginButtonPressed(){
        user.setName(userNameField.getText());
        user.setPassword(passwordField.getText());

        if (usersDAO.checkLoginAndPassword(user)){
            Router.Instance().changeView(Views.Menu);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText(" username or password not matching");
            alert.showAndWait();

        }
    }

    /**
     * Displays the "Terms & Conditions" pop up
     */
    @FXML
    private void OnRegisterButtonPressed(){
        RegisterReglement();
    }

    /**
     * Defines the "Terms & Conditions" pop up
     */
    public void RegisterReglement(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reglement");
        alert.setHeaderText("Do you accept terms and conditions");
        alert.setContentText(" Les présentes « conditions générales d'utilisation » ont pour objet" +
                " l'encadrement juridique des modalités de mise à disposition des services du site FeedBuzz" +
                " et leur utilisation par « l'Utilisateur ».\n" +
                "Les conditions générales d'utilisation doivent être acceptées par" +
                " tout Utilisateur souhaitant accéder au site. Elles constituent " +
                "le contrat entre le site et l'Utilisateur. L’accès au site par " +
                "l’Utilisateur signifie " +
                "son acceptation des présentes " +
                "conditions générales d’utilisation.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Router.Instance().changeView(Views.Register);


        }
    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

}
