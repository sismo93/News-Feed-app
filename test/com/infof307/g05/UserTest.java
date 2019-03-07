package com.infof307.g05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User("test", "password", "test@test.com");

    @Test
    void getUsernameTest() {
        assertEquals("test", user.getUsername());
    }

    @Test
    void setUsernameTest() {
        user.setUsername("newTest");
        assertEquals("newTest", user.getUsername());
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
        assertEquals("test@test.com", user.getEmail());
    }

    @Test
    void setEmailTest() {
        user.setEmail("newTest@test.com");
        assertEquals("newTest@test.com", user.getEmail());
    }
}