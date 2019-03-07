package com.infof307.g05;

/**
 *@author @mouscb
 * @codereview @tvincent
 */
public class Users {
    private String mail;
    private String password;
    private String login ;

    public Users(){}
    public Users(String mail, String login, String password ){
        this.mail = mail;
        this.password = password;
        this.login = login;

    }
    public  String getMail() {
        return mail;
    }
    public String getPassword() {
        return password;
    }
    public String getLogin() {
        return login;
    }
    public  void setMail(String mail){
        this.mail= mail;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setLogin(String login){
        this.login = login;
    }
}


