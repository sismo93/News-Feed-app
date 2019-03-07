package com.infof307.g05;

public class Main {
    public static void main(String[] args) {
        User user;
        user = new User("djdjjddd@","mouddu","fhfhfh");
        DAO<User> UsersDAO = new UsersDAO();
        //boolean bol = UsersDAO.findLogin(user);
        UsersDAO.checkLoginAndPassword(user);
        System.out.println(UsersDAO.checkLoginAndPassword(user));




    }
}
