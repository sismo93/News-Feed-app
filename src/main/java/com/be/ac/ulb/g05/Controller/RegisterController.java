package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Controller of the Register
 * @author Mouscb
 * @codereview @@MnrBn
 */
public class RegisterController extends Controller {

    /**
     * Username field
     */
    @FXML
    public TextField username;

    /**
     * Password field
     */
    @FXML
    public PasswordField password;

    /**
     * Confirm password field
     */
    @FXML
    public PasswordField confirmPassword;

    /**
     * Email field
     */
    @FXML
    public TextField email;

    /**
     * Confirm email field
     */
    @FXML
    public TextField confirmMail;

    @FXML
    public StackPane stackPane;

    /**
     * Users object
     */
    private Users user;

    /**
     * Users DAO
     */
    private DAO<Users> usersDAO;


    /**
     * Constructor
     */
    public RegisterController(){
        user = new Users();
        usersDAO = new UsersDAO();
    }

    /**
     * Defines the register button
     * Checks whether the user already exists in the database
     * Otherwise, add in the database
     */
    @FXML
    private void RegisterButton() {
        if (!isMatch(email.getText(), confirmMail.getText())) {
            notificationMessage("Emails do not match");
        }

        if (!isMatch(password.getText(), confirmPassword.getText())) {
            notificationMessage("Passwords do not match");
        }

        if (isEmpty()) {
            notificationMessage("Please fill in all fields");
        } else {
            if (!usersDAO.loginExist(user) && !usersDAO.mailExist(user)){
                user.setName(username.getText());
                user.setPassword(password.getText());
                user.setMail(email.getText());

                usersDAO.add(user); // Add in database
                RootController.Instance().changeView(RootController.Views.Menu); // Go to menu
            }
            else {
                notificationMessage("notInDb");
            }
        }
    }

    /**
     *
     * @param error
     * Show a notification with the right error
     */
    private void notificationMessage(String error){
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Error"));
        content.setBody(new Text(error));

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Ok");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        content.setActions(button);
        dialog.show();
    }

    /**
     * @return true if the user forgot to fill a box
     */
    private boolean isEmpty(){
        return username.getText().trim().isEmpty() || password.getText().trim().isEmpty() ||
                email.getText().trim().isEmpty();
    }

    /**
     * @param field1 first field
     * @param field2 confirmation field
     * @return true if fields match, otherwise not
     */
    private boolean isMatch(String field1, String field2) {
        return field1.equals(field2);
    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }}
