package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {

  public static void scrollToWebElement(WebElement element, WebDriver driver) {
    WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].scrollIntoView(true);", element);
  }

}
