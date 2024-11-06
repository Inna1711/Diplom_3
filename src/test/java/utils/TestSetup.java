package utils;

import models.Constants;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class TestSetup {
    public static WebDriver setupDriver(String url) {

        var webBrowser = System.getenv(Constants.BROWSER_NAME_ENV_NAME);
        var webBrowserBinary = System.getenv(Constants.BROWSER_EXECUTABLE_ENV_NAME);

        if (Objects.equals(webBrowser, null) | Objects.equals(webBrowserBinary, null)){
            throw new InvalidArgumentException("Both " + Constants.BROWSER_NAME_ENV_NAME + " and " + Constants.BROWSER_EXECUTABLE_ENV_NAME + " envs should be set");
        }

        return getDriver(url, webBrowserBinary, webBrowser);
    }

    private static WebDriver getDriver(String url, String webBrowserBinary, String webBrowser) {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(webBrowserBinary);

        if (webBrowser.equals(Constants.YANDEX_BROWSER)){
            options.addArguments("--disable-infobars", "--profile-directory=Profile 1");
        }
        else if (webBrowser.equals(Constants.CHROME_BROWSER)){
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        } else {
            throw new InvalidArgumentException("Wrong type of browser expected 'chrome' or 'yandex' but got a: " + webBrowser);
        }

        WebDriver driver =  new ChromeDriver(options);
        driver.get(url);
        return driver;
    }
}
