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

    private final By loadingOverlay = By.className("Modal_modal_overlay__x2ZCr");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkIfMainPageLoaded() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(userAccount));
        var overlay = driver.findElement(loadingOverlay);
        if (overlay != null) {
            new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOf(overlay));
        }
        new WebDriverWait(driver, 3);
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
