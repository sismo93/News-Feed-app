package com.infof307.g05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User model
 * @author @iyamani
 * @codereview @Tanvirul.Hoque
 */
public class User {
    private String username;
    private String password;
    private String email;

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Pattern pattern;
    private Matcher matcher;

    /**
     * Constructor
     * @param username the name of the user
     * @param password the password of the user
     * @param email the email of the user
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Getter for username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for user's password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for user's password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for user's email
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for user's email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
