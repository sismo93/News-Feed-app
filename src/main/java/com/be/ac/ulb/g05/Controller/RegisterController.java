package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Controller.Router.Views;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.Model.DAO;
import com.be.ac.ulb.g05.Model.Users;
import com.be.ac.ulb.g05.Model.UsersDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import static com.be.ac.ulb.g05.Controller.AddController.showAlert;


/**
 * AbstractController of the Register
 * @author Mouscb
 * @codereview @@MnrBn
 */
public class RegisterController extends AbstractController {

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
            showAlert("Emails do not match", "error");
        }

        if (!isMatch(password.getText(), confirmPassword.getText())) {
            showAlert("Passwords do not match", "error");
        }

        if (isEmpty()) {
            showAlert("Please fill in all fields", "error");
        } else {
            if (!usersDAO.loginExist(user) && !usersDAO.mailExist(user)){
                user.setName(username.getText());
                user.setPassword(password.getText());
                user.setMail(email.getText());

                usersDAO.add(user); // Add in database
                Router.Instance().changeView(Views.Menu); // Go to menu
            }
            else {
                showAlert("Not in database", "error");
            }
        }
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

    /**
     * Sets up the article service
     * @param articleService article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }}
