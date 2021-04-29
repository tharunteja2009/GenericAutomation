package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver",baseLocation+"//src//test//resources//executables//chromedriver.exe");
        System.setProperty("webdriver.gecko.driver",baseLocation+"//src//test//resources//executables//geckodriver.exe");
        System.out.println("Initializing Chrome Driver");
        System.setProperty("WebDriver.chrome.driver", "\\path to ChromeDriver\\ChromeDriver.exe");
        return new ChromeDriver(getChromeOptions());
    }

    private ChromeOptions getChromeOptions() {
        // A few valid Options for Chrome, showcase purpose.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-features=EnableEphemeralFlashPermission");
        options.addArguments("--disable-infobars");

        return options;
    }
}
