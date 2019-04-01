package com.be.ac.ulb.infof307.g05;

import com.be.ac.ulb.g05.Model.Users;
import com.be.ac.ulb.g05.Model.UsersDAO;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @mnrbn
 * @mouscb
 */
public class UsersDAOTest extends ApplicationTest {

    private Users user;
    private UsersDAO userDao;

    /**
     * Constructor
     */
    public UsersDAOTest(){
        user = new Users("test@test.com","test","test");
        userDao = new UsersDAO();
    }

    /**
     * Test if a user is added to the database
     */
    @Test
    public void AddTest() {
        if (!userDao.loginExist(user) && (!userDao.mailExist(user))) {
            userDao.add(user);

        }
        assertTrue(userDao.checkLoginAndPassword(user));
    }


    /**
     * Test if the login exist in the database
     */
    @Test
    public void LoginExistTest() {
        user = new Users("login@test.com","login","login");
        userDao.add(user);
        assertTrue(userDao.loginExist(user));
    }

    /**
     * Test of the mail exist in the database
     */
    @Test
    public void MailExistTest() {
        user = new Users("Mail@test.com","Mail","Mail");
        userDao.add(user);
        assertTrue(userDao.mailExist(user));
    }

    /**
     * Check if the login/Password match in the database
     */
    @Test
    public void CheckLoginAndPasswordTest() {
        user = new Users("Match@test.com","Match","Match");
        userDao.add(user);
        assertTrue(userDao.checkLoginAndPassword(user));
    }
}