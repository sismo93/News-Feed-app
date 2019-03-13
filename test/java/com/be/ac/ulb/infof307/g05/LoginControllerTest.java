package be.ac.ulb.infof307.g05;
import com.be.ac.ulb.g05.Model.*;
import com.be.ac.ulb.g05.Controller.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;



import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @mnrbn
 * @borsalinoK
 * Test login class
 */
public class LoginControllerTest extends ApplicationTest {


    private  Stage stage;
    private LoginController controller;

    /**
     * @param stage
     * @throws Exception
     * load the right scene
     */
    @Override
    public void start (Stage stage) throws Exception {

        this.stage = stage;

        FXMLLoader loader = new FXMLLoader(LoginControllerTest.this.getClass().getResource("LoginFxml.fxml"));
        Parent mainNode = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();


    }

    @Before
    public void setUp () throws Exception {

    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();

    }


    /**
     * check if the field username match what we write
     */
    @Test
    public void isUsernameEmpty () {
        clickOn("#userNameField");
        write("username");
        assertEquals(controller.userNameField.getText(),"username");
    }

    /**
     * check if the field password match what we write
     */
    @Test
    public void isPasswordEmpty(){
        clickOn("#passwordField");
        write("password");
        assertEquals(controller.passwordField.getText(),"password");
    }

    @Test
    public void isLoginClicked(){
        clickOn("#userNameField");
        write("admin");
        clickOn("#passwordField");
        write("admin");
        clickOn("#LoginButton");



    }

    @Test
    public void isRegisterClicked(){
        clickOn("#RegisterButton");
    }


}
