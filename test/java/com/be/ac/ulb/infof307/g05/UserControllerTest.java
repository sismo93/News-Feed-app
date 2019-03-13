package be.ac.ulb.infof307.g05;

import com.be.ac.ulb.g05.Model.*;
import com.be.ac.ulb.g05.Controller.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    Users user = new Users("test@test.com", "test", "password");
    UserController userController = new UserController(user);

    @Test
    public  void setUsernameTest() {
        userController.setUsername("newTest");
        assertEquals("newTest", userController.getUsername());
    }

    @Test
    public void getUsernameTest() {
        assertEquals("test", userController.getUsername());
    }

    @Test
    void setPasswordTest() {
        userController.setPassword("newPassword");
        assertEquals("newPassword", userController.getPassword());
    }

    @Test
    void getPasswordTest() {
        assertEquals("password", userController.getPassword());
    }

    @Test
    void setEmailTest() {
        userController.setEmail("newTest@test.com");
        assertEquals("newTest@test.com", userController.getEmail());
    }

    @Test
    void getEmailTest() {
        assertEquals("test@test.com", userController.getEmail());
    }
}