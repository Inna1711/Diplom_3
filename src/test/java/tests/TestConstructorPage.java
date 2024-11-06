package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import locators.ConstructorPage;
import models.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.TestSetup;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestConstructorPage {
    private WebDriver driver;

    @Before
    public void setUp(){
        this.driver = TestSetup.setupDriver(Constants.MAIN_PAGE);
    }


    @After
    public void cleanup(){
        driver.quit();
    }

    @Step("Clicking other button from buns")
    public void clickOtherButton(){
        var constructorPOM = new ConstructorPage(driver);
        constructorPOM.clickIngredientsButton();
        assertFalse("Buns shouldn't be selected!", constructorPOM.isBunsButtonSelected());
    }

    @Test
    @DisplayName("Test buns from label button")
    @Description("Open buns clicking sauces button and then buns label button")
    public void testOpenBuns(){
        var constructorPOM = new ConstructorPage(driver);
        constructorPOM.isPageOpened();
        clickOtherButton();
        constructorPOM.clickBunsButton();
        assertFalse("Sauces shouldn't be selected", constructorPOM.isSaucesButtonSelected());
        assertTrue("Buns should be selected", constructorPOM.isBunsButtonSelected());
        assertFalse("Ingredients shouldn't be selected", constructorPOM.isIngredientsButtonSelected());
    }

    @Test
    @DisplayName("Test sauces from label button")
    @Description("Open sauces clicking sauces label button")
    public void testOpenSauces(){
        var constructorPOM = new ConstructorPage(driver);
        constructorPOM.isPageOpened();
        constructorPOM.clickSaucesButton();

        assertTrue("Sauces should be selected", constructorPOM.isSaucesButtonSelected());
        assertFalse("Buns shouldn't be selected", constructorPOM.isBunsButtonSelected());
        assertFalse("Ingredients shouldn't be selected", constructorPOM.isIngredientsButtonSelected());
    }

    @Test
    @DisplayName("Test ingredients from label button")
    @Description("Open ingredients clicking ingredients label button")
    public void testOpenIngredients(){
        var constructorPOM = new ConstructorPage(driver);
        constructorPOM.isPageOpened();
        constructorPOM.clickIngredientsButton();

        assertFalse("Sauces shouldn't be selected", constructorPOM.isSaucesButtonSelected());
        assertFalse("Buns shouldn't be selected", constructorPOM.isBunsButtonSelected());
        assertTrue("Ingredients should be selected", constructorPOM.isIngredientsButtonSelected());
    }
}
