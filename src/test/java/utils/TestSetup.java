package utils;

import models.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestSetup {
    public static WebDriver setupDriver(String url, String webBrowser) {

        ChromeOptions options = new ChromeOptions();
        if (webBrowser.equals(Constants.YANDEX_BROWSER)){
            options.addArguments("--headless", "--disable-infobars", "--profile-directory=Profile 1");
            options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");
        }
        else if (webBrowser.equals(Constants.CHROME_BROWSER)){
            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
            options.setBinary("C:\\chrome-distr\\chrome.exe");
        }
        WebDriver driver =  new ChromeDriver(options);
        driver.get(url);
        return driver;
    }
}
