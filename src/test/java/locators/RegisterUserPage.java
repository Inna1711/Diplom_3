package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterUserPage {
    private final WebDriver driver;

    private final By loginSelect = By.xpath("//label[text()='Имя']//parent::div/input");
    private final By emailSelect = By.xpath("//label[text()='Email']//parent::div/input");
    private final By passwordSelect = By.xpath("//input[@name='Пароль']");
    private final By registerUser = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath("//a[text()='Войти']");
    private final By successButton = By.xpath("//button[text()='Войти']");

    private final By loadingOverlay = By.className("Modal_modal_overlay__x2ZCr");


    public RegisterUserPage(WebDriver driver){
        this.driver = driver;
    }

    public void setLogin(String login){
        driver.findElement(loginSelect).sendKeys(login);
    }

    public void setEmail(String email){
        driver.findElement(emailSelect).sendKeys(email);
    }

    public void setPassword(String password){
        driver.findElement(passwordSelect).sendKeys(password);
    }

    public void clickRegisterButton(){
        driver.findElement(registerUser).click();
    }

    public void clickLoginButton(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        var overlay = driver.findElement(loadingOverlay);
        if (overlay != null) {
            new WebDriverWait(driver, 3).until(ExpectedConditions.invisibilityOf(overlay));
        }
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public boolean isRegistered(){
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(successButton));
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
