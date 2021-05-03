package Pages;

import Exceptions.GenericAutomationException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

  private WebDriver driver;

  @FindBy(how = How.XPATH, using = "//input[@type='file']/..")
  private WebElement btnChooseFile;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clickOnChooseFile() throws GenericAutomationException {
    WebDriverWait webDriverWait = new WebDriverWait(this.driver, 10);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].scrollIntoView(true);", btnChooseFile);
    if (btnChooseFile.isDisplayed()) {
      btnChooseFile.click();
    } else {
      throw new GenericAutomationException("WebElement btnChooseFile not found");
    }
  }

}
