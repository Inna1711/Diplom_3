package tests;

import fixtures.ApiFixture;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import locators.LoginUserPage;
import locators.MainPage;
import locators.RegisterUserPage;
import models.Constants;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import utils.TestSetup;


@RunWith(Parameterized.class)
public class TestLoginPage {
    private WebDriver driver;
    private String browserName;
    private final User testUser = new User(Constants.TEST_USER_NANE, Constants.TEST_USER_EMAIL, Constants.TEST_USER_CORRECT_PASSWORD);

    @Before
    public void setUp(){
        RestAssured.baseURI = Constants.BASE_URL;
        this.driver = TestSetup.setupDriver(Constants.MAIN_PAGE, browserName);
    }

    @Before
    @Step("Create user")
    public void createUser(){
        RestAssured.baseURI = Constants.BASE_URL;
        ApiFixture.createUserHandler(testUser);
    }

    @After
    @Step("Delete user")
    public void deleteUser(){
        ApiFixture.deleteUser(testUser);
    }

    @Step("Check if page is opened")
    private void checkIfPageOpened(){
        var loginPOM = new LoginUserPage(driver);
        loginPOM.waitPageLoad();
    }

    @Step("Login user")
    public void loginUser(){
        var loginPOM = new LoginUserPage(driver);
        loginPOM.fillParams(testUser);
        loginPOM.clickLogin();
    }

    @Step("Verify login")
    public void isLoggedIn(){
        var mainPOM = new MainPage(driver);
        mainPOM.checkIfLoggedIn();
    }

    @Parameterized.Parameters
    public static String[][] initializeTest(){
        return new String[][] {
                {
                        "yandex"
                },
                {
                        "chrome"
                }
        };
    }

    public TestLoginPage(String browserName){
        this.browserName = browserName;
    }

    @Test
    public void testUserLoginWithMainLoginButton(){
        var mainPOM = new MainPage(driver);
        mainPOM.clickLoginButton();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }

    @Test
    public void testUserLoginWithUserAccountButton(){
        var mainPOM = new MainPage(driver);
        mainPOM.clickUserAccount();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }

    @Test
    public void testUserLoginWithRegisterForm(){
        driver.get(Constants.REGISTER_PAGE_URL);
        var registerPOM = new RegisterUserPage(driver);
        registerPOM.clickLoginButton();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }

    @Test
    public void testUserLoginWithForgotPassword(){
        driver.get(Constants.FORGOT_PASSWORD_PAGE);
        var loginPOM = new LoginUserPage(driver);
        loginPOM.clickLoginUrlFromForgotPassword();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }
}
