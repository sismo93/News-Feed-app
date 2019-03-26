package com.be.ac.ulb.g05.Model;

/**
 * User model
 *@author @iyamani, @mouscb
 * @codereview @vtombou
 */
public class Users {

    /**
     * User email
     */
    private String mail;

    /**
     * User password
     */
    private String password;

    /**
     * User name
     */
    private String name;

    public Users(){}

    /**
     * Constructor
     * @param mail the email of the user
     * @param name the username of the user
     * @param password the password of the user
     */
    public Users(String mail, String name, String password) {
        this.mail = mail;
        this.password = password;
        this.name = name;

    }

    /**
     * Getter for email
     * @return user email
     */
    public  String getMail() {
        return this.mail;
    }

    /**
     * Getter for password
     * @return user password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter for login
     * @return user login
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for user email
     * @param mail user email
     */
    public  void setMail(String mail){
        this.mail = mail;
    }

    /**
     * Setter for user password
     * @param password user password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Setter for user login
     * @param name username
     */
    public void setName(String name){
        this.name = name;
    }
}


