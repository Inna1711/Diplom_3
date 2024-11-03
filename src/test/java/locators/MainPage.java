package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By userAccount = By.xpath("//p[text()='Личный Кабинет']//parent::a");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void clickUserAccount(){
        driver.findElement(userAccount).click();
    }

    public void checkIfLoggedIn(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }
}
