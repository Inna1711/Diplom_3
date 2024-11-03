package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserAccountPage {
    private final WebDriver driver;

    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By userName = By.xpath("//label[text()='Имя']//parent::div//input");
    private final By userEmail = By.xpath("//label[text()='Логин']//parent::div//input");
    private final By constructorPage = By.xpath("//p[text()='Конструктор']//parent::a");
    private final By logoPage = By.className("AppHeader_header__logo__2D0X2");

    public UserAccountPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkAccountLoaded(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(userName));
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(userEmail));
    }

    public void openConstructorPageWithLabel(){
        driver.findElement(constructorPage).click();
    }

    public void openConstructorPageWithIcon(){
        driver.findElement(logoPage).click();
    }

    public void clickLogoutPage(){
        driver.findElement(logoutButton).click();
    }
}
