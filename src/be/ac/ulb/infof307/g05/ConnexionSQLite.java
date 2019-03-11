package be.ac.ulb.infof307.g05;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    //This function is used to load the connection driver between SQLite and JDBC
    private static void loadDriver(){
        try{
            //Driver loading
            Class.forName("org.sqlite.JDBC");
        }catch (ClassNotFoundException e){

        }

    }


    //Method that will return us our Connection instance or create one if it does not exist
    public static Connection getInstance() {

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
