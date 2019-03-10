package be.ac.ulb.infof307.g05;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegisterController {
    public TextField userName;
    public PasswordField password;
    public TextField email;

    private Users user;
    private DAO<Users> usersDAO;
    private Alert alert;


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
    private void RegisterButton(){
        user.setLogin(userName.getText());
        user.setPassword(password.getText());
        user.setMail(email.getText());


        if (isEmpty()){
            notificationMessage("empty");
        }
        else{
            if (!usersDAO.loginExist(user) && !usersDAO.mailExist(user)){
                usersDAO.add(user); // Add in database
                Router.Instance().changeView(Router.Views.Menu); // Go to menu
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
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        if (error =="empty"){
            alert.setContentText("All boxes must be filled");
        }
        else{
            alert.setContentText("This account already exist");
        }

        alert.showAndWait();
    }

    /***
     *
     * @return true if the user forgot to fill a box
     */
    private boolean isEmpty(){


        if (userName.getText().trim().isEmpty() ||
                password.getText().trim().isEmpty() ||
                email.getText().trim().isEmpty()){

            return true;
        }
        return false;
    }
}
