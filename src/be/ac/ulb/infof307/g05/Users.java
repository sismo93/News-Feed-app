package be.ac.ulb.infof307.g05;

/**
 * User model
 *@author @iyamani, @mouscb
 * @codereview @tvincent
 */
public class Users {
    private String mail;
    private String password;
    private String login;

    public Users(){}

    /**
     * Constructor
     * @param mail the email of the user
     * @param login the username of the user
     * @param password the password of the user
     */
    public Users(String mail, String login, String password) {
        this.mail = mail;
        this.password = password;
        this.login = login;

    }

    /**
     * Getter for email
     * @return user email
     */
    public  String getMail() {
        return mail;
    }

    /**
     * Getter for password
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter for login
     * @return user login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for user email
     * @param mail
     */
    public  void setMail(String mail){
        this.mail = mail;
    }

    /**
     * Setter for user password
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Setter for user login
     * @param login
     */
    public void setLogin(String login){
        this.login = login;
    }
}


