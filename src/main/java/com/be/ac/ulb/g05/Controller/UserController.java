package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;

/**
 * User controller
 * @author @iyamani
 * @codereview @Tanvirul.Hoque
 */
public class UserController extends AbstractController {
    private Users model;


    /**
     * Constructor
     * @param model User model
     */
    public UserController(Users model) {
        this.model = model;
    }

    /**
     * Setter for username
     * @param username user's name
     */
    public void setUsername(String username) {
        model.setName(username);
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUsername() {
        return model.getName();
    }

    /**
     * Setter for user's password
     * @param password user's password
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
     * @param email user email
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


    /**
     * Sets up the article service
     * @param articleService Article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }


}
