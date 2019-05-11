package com.be.ac.ulb.g05;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.application.Platform;

/**
 * Connexion SQLite
 * @author @vtombou
 * @codereview @mouscb
 */
public class ConnexionSQLite{

    private static Connection connect = null;

    public ConnexionSQLite() {
        //Driver loading
        loadDriver();
    }


    /**
     * @return Connection instance
     * Method that will return us our Connection instance or create one if it does not exist
     */
    public static Connection getInstance() {

        loadDriver();

        try
        {
            // create a database connection
            connect = DriverManager.getConnection("jdbc:sqlite:groupe05.db");

        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database not found"+"\n"+e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            System.err.println(e.getMessage());
            Platform.exit();
        }

        return connect;
    }


    /**
     * This function is used to load the connection driver between SQLite and JDBC
     */
    private static void loadDriver(){
        try{
            //Driver loading
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database not functioning"+"\n"+e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            System.err.println(e.getMessage());
            Platform.exit();
        }

    }




}
