package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import locators.RegisterUserPage;
import models.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.TestSetup;

import static org.junit.Assert.assertFalse;

public class TestRegisterPage {
    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = TestSetup.setupDriver(Constants.REGISTER_PAGE_URL);
    }

    @Step("Create user")
    private void createUser(RegisterUserPage registerUserPOM){
        registerUserPOM.clickRegisterButton();
    }

    @Test
    @DisplayName("Register correct user")
    @Description("Test register user with correct data")
    public void testCorrectUserRegister(){
        var registerUserPOM = new RegisterUserPage(driver);
        registerUserPOM.setLogin(Constants.TEST_USER_NANE);
        registerUserPOM.setEmail(Constants.TEST_USER_EMAIL);
        registerUserPOM.setPassword(Constants.TEST_USER_CORRECT_PASSWORD);
        createUser(registerUserPOM);
    }

    @Test
    @DisplayName("Register incorrect user")
    @Description("Test register user with incorrect data")
    public void testIncorrectUserRegister(){
        var registerUserPOM = new RegisterUserPage(driver);
        registerUserPOM.setLogin(Constants.TEST_USER_NANE);
        registerUserPOM.setEmail(Constants.TEST_USER_EMAIL);
        registerUserPOM.setPassword(Constants.TEST_USER_INCORRECT_PASSWORD);
        createUser(registerUserPOM);
        assertFalse("User shouldn't be registered!", registerUserPOM.isRegistered()) ;
    }

    @After
    public void cleanup(){
       driver.quit();
    }
}
