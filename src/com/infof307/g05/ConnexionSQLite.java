package com.infof307.g05;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionSQLite{

    private static Connection connect = null;

    public ConnexionSQLite() {
        //Chargement du driver
        loadDriver();
    }

    //Cette fonction permet de charger le driver de connexion entre SQLite et le JDBC
    private static void loadDriver(){
        try{
            //chargement du driver
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e){

        }

    }


    //Méthode qui va nous retourner notre instance de Connection ou créer une si elle n'existe pas
    public static Connection getInstance() {
        //chargement du driver
        loadDriver();

        try
        {
            // create a database connection
            connect = DriverManager.getConnection("jdbc:sqlite:groupe05.db");

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return connect;
    }

}
