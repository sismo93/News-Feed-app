package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import com.be.ac.ulb.g05.Controller.Router.*;


/**
 * AbstractController of the Login
 * @author @MnrBn
 * @codereview @Mouscb
 */
public class LoginController extends AbstractController {

    /**
     * Password field
     */
    @FXML
    public PasswordField passwordField;

    /**
     * Username field
     */
    @FXML
    public TextField userNameField;

    @FXML
    public StackPane stackPane;

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
     * Sets up the article service
     * @param articleService article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
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
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error"));
            content.setBody(new Text("Credentials do not match database entry"));

            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("Ok");

            button.setOnAction(event -> dialog.close());

            content.setActions(button);
            dialog.show();
        }
    }

    /**
     * Displays the "Terms & Conditions" pop up
     */
    @FXML
    private void OnRegisterButtonPressed() {
        RegisterTerm();
    }

    /**
     * Defines the "Terms & Conditions" pop up
     */
    private void RegisterTerm(){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Règlement"));
        content.setBody(new Text("Les présentes « conditions générales\n d'utilisation » ont pour objet" +
                " l'encadrement juridique\n des modalités de mise à disposition\n des services du site FeedBuzz" +
                " et leur utilisation par « l'Utilisateur ».\n" +
                "Les conditions générales\n d'utilisation doivent être acceptées par" +
                " tout Utilisateur souhaitant accéder\n au site. Elles constituent " +
                "le contrat entre le site et l'Utilisateur.\n L’accès au site par " +
                "l’Utilisateur signifie son acceptation\n des présentes " +
                "conditions générales d’utilisation."));

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton acceptButton = new JFXButton("Accept");
        JFXButton declineButton = new JFXButton("Decline");

        acceptButton.setOnAction(event -> Router.Instance().changeView(Views.Register));

        declineButton.setOnAction(event -> dialog.close());
        content.setActions(acceptButton, declineButton);
        dialog.show();
    }


}
