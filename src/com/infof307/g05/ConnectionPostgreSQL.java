package com.infof307.g05;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *@author @mouscb
 * @codereview @tvincent
 */
public class ConnectionPostgreSQL{

    /**
     * URL of connection
     */

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    /**
     * User's name
     */
    private static String user = "postgres";
    /**
     * Password of the user
     */
    private static String passwd = "azerty";
    /**
     * Objet Connection
     */
    private static Connection connect;

    /**
     * Method that will return us our instance
     * and create it if it does not exist ...
     * @return
     */
    public static Connection getInstance(){
        if(connect == null){
            try {
                System.out.print("first statement. ");
                Class.forName("org.postgresql.Driver");

                connect = DriverManager.getConnection(url, user, passwd);
                System.out.print("2 statement. ");
            }  catch (Exception e) {

                e.printStackTrace();

            }
        }
        return connect;
    }
}

