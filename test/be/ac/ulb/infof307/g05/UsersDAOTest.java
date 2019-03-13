package be.ac.ulb.infof307.g05;

import be.ac.ulb.infof307.g05.Model.Users;
import be.ac.ulb.infof307.g05.Model.UsersDAO;
import junit.framework.TestCase;

/**
 * @mnrbn
 * @mouscb
 */
public class UsersDAOTest extends TestCase {
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
    public void testAdd() {
        if (!userDao.loginExist(user) && (!userDao.mailExist(user))) {
            userDao.add(user);

        }
        assertTrue(userDao.checkLoginAndPassword(user));
    }


    /**
     * Test if the login exist in the database
     */
    public void testLoginExist() {
        user = new Users("login@test.com","login","login");
        userDao.add(user);
        assertTrue(userDao.loginExist(user));
    }

    /**
     * Test of the mail exist in the database
     */
    public void testMailExist() {
        user = new Users("Mail@test.com","Mail","Mail");
        userDao.add(user);
        assertTrue(userDao.mailExist(user));
    }

    /**
     * Check if the login/Password match in the database
     */
    public void testCheckLoginAndPassword() {
        user = new Users("Match@test.com","Match","Match");
        userDao.add(user);
        assertTrue(userDao.checkLoginAndPassword(user));
    }
}