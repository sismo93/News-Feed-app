package com.infof307.g05;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *@author @mouscb
 * @codereview @tvincent
 */
public class UsersDAO extends DAO<User> {

    public void add(User obj) {
        try {

            PreparedStatement prepare2 = this.connect.prepareStatement(
                    "INSERT INTO users (email,password,login)"+
                            "VALUES (?,md5(?),?)");
            prepare2.setString(1,obj.getEmail());
            prepare2.setString(2,obj.getPassword());
            prepare2.setString(3,obj.getUsername());

            prepare2.executeUpdate();


        }
        catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public boolean loginExist(User obj) {
        try {

            PreparedStatement stmt = this.connect.prepareStatement(
                    "select * from users where login = ? ");
            stmt.setString(1, obj.getUsername());
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

    @Override
    public boolean mailExist(User obj) {
        try {

            PreparedStatement stmt = this.connect.prepareStatement(
                    "select * from users where email = ? ");
            stmt.setString(1, obj.getEmail());
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

    public boolean checkLoginAndPassword(User obj) {
        try {

            PreparedStatement stmt = this.connect.prepareStatement(
                    "select * from users where login = ? and password = md5(?)");
            stmt.setString(1, obj.getUsername());
            stmt.setString(2, obj.getPassword());
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

