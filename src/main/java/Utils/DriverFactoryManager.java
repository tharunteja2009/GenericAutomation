package Utils;

import com.google.common.base.Preconditions;
import org.openqa.selenium.WebDriver;

public class DriverFactoryManager {
    static String browser;

    public static WebDriver getWebDriver() {
        browser = getBrowserName();
        switch (browser.toUpperCase()) {
            case "CHROME":
                return DriverFactory.CHROME.getDriverManager().getDriver();
            case "FIREFOX":
                return DriverFactory.FIREFOX.getDriverManager().getDriver();
            default:
                System.out.println(browser + " not supported");
        }
        return null;
    }

    public static void quitDriver(){
        Preconditions.checkArgument(!browser.isEmpty(),"browser name must be derived");
        switch (browser.toUpperCase()) {
            case "CHROME":
                 DriverFactory.CHROME.getDriverManager().quitDriver();
                 break;
            case "FIREFOX":
                 DriverFactory.FIREFOX.getDriverManager().quitDriver();
                 break;
            default:
                System.out.println(browser + " not supported");
                break;
        }
    }

    public static String getBrowserName(){
        if (null!=System.getProperty("browser")) {
            // if found in vm arg
            browser = System.getProperty("browser");
        } else {
            //or load from config file default browser
            browser = PropertyManager.browser;
        }
        return browser;
    }
}
