package tests;

import fixtures.ApiFixture;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import locators.*;
import models.Constants;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.TestSetup;


public class TestUserAccountPage {
    private WebDriver driver;
    private final User testUser = new User(Constants.TEST_USER_NANE, Constants.TEST_USER_EMAIL, Constants.TEST_USER_CORRECT_PASSWORD);


    @Before
    @Step("Create and login user")
    public void createUser(){
        RestAssured.baseURI = Constants.BASE_URL;
        ApiFixture.createUserHandler(testUser);
        this.driver = TestSetup.setupDriver(Constants.MAIN_PAGE);
        var loginPOM = new LoginUserPage(driver);
        var mainPOM = new MainPage(driver);
        driver.get(Constants.MAIN_PAGE);
        mainPOM.clickLoginButton();
        loginPOM.waitPageLoad();
        loginPOM.fillParams(testUser);
        loginPOM.clickLogin();
        mainPOM.checkIfLoggedIn();
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


    @Test
    @DisplayName("Open user account page")
    @Description("Open user account page clicking on label")
    public void testOpeningUserAccount(){
        driver.get(Constants.MAIN_PAGE);
        var mainPOM = new MainPage(driver);
        var userAccountPOM = new UserAccountPage(driver);
        mainPOM.checkIfMainPageLoaded();
        mainPOM.clickUserAccount();
        userAccountPOM.checkAccountLoaded();
    }

    @Test
    @DisplayName("Open constructor from label button")
    @Description("Open constructor clicking label button")
    public void testOpeningConstructorFromLabel(){
        driver.get(Constants.MAIN_PAGE);
        var mainPOM = new MainPage(driver);
        var userAccountPOM = new UserAccountPage(driver);
        var constructorPOM = new ConstructorPage(driver);
        mainPOM.checkIfMainPageLoaded();
        mainPOM.clickUserAccount();
        userAccountPOM.checkAccountLoaded();
        userAccountPOM.openConstructorPageWithLabel();
        constructorPOM.isPageOpened();
    }

    @Test
    @DisplayName("Open constructor from logo ")
    @Description("Open constructor clicking logo")
    public void testOpeningConstructorFromLogo(){
        driver.get(Constants.MAIN_PAGE);
        var mainPOM = new MainPage(driver);
        var userAccountPOM = new UserAccountPage(driver);
        var constructorPOM = new ConstructorPage(driver);
        mainPOM.checkIfMainPageLoaded();
        mainPOM.clickUserAccount();
        userAccountPOM.checkAccountLoaded();
        userAccountPOM.openConstructorPageWithIcon();
        constructorPOM.isPageOpened();
    }

    @Test
    @DisplayName("Logout from account")
    @Description("Logout from account clicking on button")
    public void testLogout(){
        driver.get(Constants.MAIN_PAGE);
        var mainPOM = new MainPage(driver);
        var userAccountPOM = new UserAccountPage(driver);
        var userLoginPOM = new LoginUserPage(driver);
        mainPOM.checkIfMainPageLoaded();
        mainPOM.clickUserAccount();
        userAccountPOM.checkAccountLoaded();
        userAccountPOM.clickLogoutPage();
        userLoginPOM.waitPageLoad();
    }
}
