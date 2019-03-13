package be.ac.ulb.infof307.g05.Controller;

import be.ac.ulb.infof307.g05.Model.Users;

/**
 * User controller
 * @author @iyamani
 * @codereview @Tanvirul.Hoque
 */
public class UserController {
    private Users model;

    /**
     * Constructor
     * @param model
     */
    public UserController(Users model) {
        this.model = model;
    }

    /**
     * Setter for username
     * @param username
     */
    public void setUsername(String username) {
        model.setLogin(username);
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return model.getLogin();
    }

    /**
     * Setter for user's password
     * @param password
     */
    public void setPassword(String password) {
        model.setPassword(password);
    }

    /**
     * Getter for user's password
     * @return user's password
     */
    public String getPassword() {
        return model.getPassword();
    }

    /**
     * Setter for user's email
     * @param email
     */
    public void setEmail(String email) {
        model.setMail(email);
    }

    /**
     * Getter for user's email
     * @return user's email
     */
    public String getEmail() {
        return model.getMail();
    }

}
