package com.infof307.g05;

public class UserController {
    private User model;
    private UserView view;

    public UserController(User model, UserView view) {
        this.model = model;
        this.view = view;
    }

    public void setUsername(String username) {
        model.setUsername(username);
    }

    public String getUsername() {
        return model.getUsername();
    }

    public void setPassword(String password) {
        model.setPassword(password);
    }

    public String getPassword() {
        return model.getPassword();
    }

    public void setEmail(String email) {
        model.setEmail(email);
    }

    public String getEmail() {
        return model.getEmail();
    }

    public void updateView() {
        view.printUserDetails(model.getUsername(), model.getEmail());
    }

}
