package com.infof307.g05;

/**
 * User controller
 * @author @iyamani
 * @codereview @Tanvirul.Hoque
 */
public class UserController {
    private User model;
    private UserView view;

    /**
     * Constructor
     * @param model
     * @param view
     */
    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Setter for username
     * @param username
     */
    public void setUsername(String username) {
        model.setUsername(username);
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return model.getUsername();
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
        model.setEmail(email);
    }

    /**
     * Getter for user's email
     * @return user's email
     */
    public String getEmail() {
        return model.getEmail();
    }

    /**
     * updates the view of the User model
     */
    public void updateView() {
        view.printUserDetails(model.getUsername(), model.getEmail());
    }

}
