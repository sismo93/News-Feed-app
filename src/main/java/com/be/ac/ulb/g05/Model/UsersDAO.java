package com.be.ac.ulb.g05.Model;

import com.be.ac.ulb.g05.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author @mouscb
 * @codereview @vtombou
 */
public class UsersDAO extends DAO<Users> {

    /**
     * Adds user (login, password & email) to the database
     * @param obj User object
     */
    public void add(Users obj) {
        try {
            PreparedStatement prepare2 = this.connect.prepareStatement(
                    "INSERT INTO users (email, password, login)" +
                            "VALUES (?,?,?)");
            prepare2.setString(1,obj.getMail());
            prepare2.setString(2, HashMD5.hashFunct(obj.getPassword()));
            prepare2.setString(3,obj.getName());
            prepare2.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the user exists in the database or not
     * @param obj Users objects
     * @return true if user exists, false if it doesn't exist
     */
    @Override
    public boolean loginExist(Users obj) {
        try {
            PreparedStatement stmt = this.connect.prepareStatement(
                    "select * from users where login = ? ");
            stmt.setString(1, obj.getName());
            ResultSet rs=stmt.executeQuery();

            boolean recordAdded = false;
            while(rs.next()){
                recordAdded = true;
            }
            return recordAdded;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks whether the mail exists in the database or not
     * @param obj Users objects
     * @return true if mail exists, false if it doesn't exist
     */
    @Override
    public boolean mailExist(Users obj) {
        try {
            PreparedStatement stmt = this.connect.prepareStatement(
                    "select * from users where email = ? ");
            stmt.setString(1, obj.getMail());
            ResultSet rs=stmt.executeQuery();

            boolean recordAdded = false;
            while(rs.next()){

                recordAdded = true;
            }
            return recordAdded;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if login and password match the database information
     * @param obj User object
     * @return true if credentials match database, otherwise false
     */
    public boolean checkLoginAndPassword(Users obj) {
        try {
            PreparedStatement stmt = this.connect.prepareStatement(
                    "select * from users where login = ? and password = ?");
            stmt.setString(1, obj.getName());
            stmt.setString(2, HashMD5.hashFunct(obj.getPassword()));
            ResultSet rs=stmt.executeQuery();

            boolean recordAdded = false;
            while(rs.next()){

                recordAdded = true;
            }
            return recordAdded;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

