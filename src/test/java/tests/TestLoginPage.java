package tests;

import fixtures.ApiFixture;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import locators.LoginUserPage;
import locators.MainPage;
import locators.RegisterUserPage;
import models.Constants;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import utils.TestSetup;


@RunWith(Parameterized.class)
public class TestLoginPage {
    private WebDriver driver;
    private final String  browserName;
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
    
    @After
    public void cleanup(){
        driver.quit();
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
    @DisplayName("Test login from Main page")
    @Description("Test login from Main page button")
    public void testUserLoginWithMainLoginButton(){
        var mainPOM = new MainPage(driver);
        mainPOM.checkIfMainPageLoaded();
        mainPOM.clickLoginButton();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }

    @Test
    @DisplayName("Test login from Account button")
    @Description("Test login from Main page with account button")
    public void testUserLoginWithUserAccountButton(){
        var mainPOM = new MainPage(driver);
        mainPOM.checkIfMainPageLoaded();
        mainPOM.clickUserAccount();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }

    @Test
    @DisplayName("Test login from Registration form")
    @Description("Test login from Registration form with login button")
    public void testUserLoginWithRegisterForm(){
        driver.get(Constants.REGISTER_PAGE_URL);
        var registerPOM = new RegisterUserPage(driver);
        registerPOM.clickLoginButton();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }

    @Test
    @DisplayName("Test login from forgot password form")
    @Description("Test login from forgot password form with login button")
    public void testUserLoginWithForgotPassword(){
        driver.get(Constants.FORGOT_PASSWORD_PAGE);
        var loginPOM = new LoginUserPage(driver);
        loginPOM.clickLoginUrlFromForgotPassword();
        checkIfPageOpened();
        loginUser();
        isLoggedIn();
    }
}
