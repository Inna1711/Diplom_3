package utils;

import io.restassured.RestAssured;
import models.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestSetup {
    public static WebDriver setupDriver(String url, String webBrowser) {

        ChromeOptions options = new ChromeOptions();
        if (webBrowser.equals("yandex")){
            options.addArguments("--headless", "--disable-infobars", "--profile-directory=Profile 1");
            options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");
        }
        else {
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        }
        WebDriver driver =  new ChromeDriver(options);
        driver.get(url);
        return driver;
    }
}
