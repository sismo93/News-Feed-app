package com.infof307.g05;

public class Main {
    public static void main(String[] args) {
        Users user;
        user = new Users("djdjjddd@","mouddu","fhfhfh");
        DAO<Users> UsersDAO = new UsersDAO();
        //boolean bol = UsersDAO.findLogin(user);
        UsersDAO.checkLoginAndPassword(user);
        System.out.println(UsersDAO.checkLoginAndPassword(user));




    }
}
