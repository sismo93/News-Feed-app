package be.ac.ulb.infof307.g05;


import be.ac.ulb.infof307.g05.Controller.RegisterController;
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
 * @mouscb
 * Class that test the register
 */
public class RegisterControllerTest extends ApplicationTest {

    private RegisterController controller;

    /**
     * @param stage
     * @throws Exception
     * start the scene
     */
    @Override
    public void start (Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(RegisterControllerTest.this.getClass().getResource("RegisterFxml.fxml"));
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
     * check if the username is empty
     */
    @Test
    public void isUsernameEmpty () {
        clickOn("#userName");
        write("username");
        assertEquals(controller.userName.getText(),"username");
    }

    /**
     * check if the password is empty
     */
    @Test
    public void isPasswordEmpty(){
        clickOn("#password");
        write("password");
        assertEquals(controller.password.getText(),"password");
    }

    /**
     * check if the email is empty
     */
    @Test
    public void isEmailEmpty(){
        clickOn("#email");
        write("email@email.com");
        assertEquals(controller.email.getText(),"email@email.com");
    }

}
