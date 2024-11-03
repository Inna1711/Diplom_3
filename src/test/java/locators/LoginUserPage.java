package locators;

import jdk.jshell.spi.SPIResolutionException;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUserPage {
    private final WebDriver driver;

    private final By emailSelect = By.xpath("//label[text()='Email']//parent::div/input");
    private final By passwordSelect = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By forgotPasswordLoginButton = By.xpath("//a[text()='Войти']");

    public LoginUserPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitPageLoad(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public void fillParams(User user){
        driver.findElement(emailSelect).sendKeys(user.getEmail());
        driver.findElement(passwordSelect).sendKeys(user.getPassword());
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public void clickLoginUrlFromForgotPassword(){
        driver.findElement(forgotPasswordLoginButton).click();
    }
}
