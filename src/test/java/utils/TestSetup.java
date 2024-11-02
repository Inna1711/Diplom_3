package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestSetup {
    public static WebDriver setupDriver(String url, String webBrowser) {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        if (webBrowser.equals("yandex")){
            options.addArguments("--headless", "--disable-infobars", "--profile-directory=Profile 1");
            options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");
        }
        else {
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        }
        driver =  new ChromeDriver(options);
        driver.get(url);
        return driver;
    }
}
