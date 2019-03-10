package com.infof307.g05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    Users user = new Users("test@test.com", "test", "password");

    @Test
    void getUsernameTest() {
        assertEquals("test", user.getLogin());
    }

    @Test
    void setUsernameTest() {
        user.setLogin("newTest");
        assertEquals("newTest", user.getLogin());
    }

    @Test
    void getPasswordTest() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void setPasswordTest() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void getEmailTest() {
        assertEquals("test@test.com", user.getMail());
    }

    @Test
    void setEmailTest() {
        user.setMail("newTest@test.com");
        assertEquals("newTest@test.com", user.getMail());
    }
}