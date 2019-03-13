package be.ac.ulb.infof307.g05;

import be.ac.ulb.infof307.g05.Model.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest{

    Users user = new Users("test@test.com", "test", "password");

    @Test
    public void getUsernameTest() {
        assertEquals("test", user.getLogin());
    }

    @Test
    public void setUsernameTest() {
        user.setLogin("newTest");
        assertEquals("newTest", user.getLogin());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void setPasswordTest() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void getEmailTest() {
        assertEquals("test@test.com", user.getMail());
    }

    @Test
    public void setEmailTest() {
        user.setMail("newTest@test.com");
        assertEquals("newTest@test.com", user.getMail());
    }
}