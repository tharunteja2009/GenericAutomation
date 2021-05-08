package Pages;

import Exceptions.GenericAutomationException;
import Utils.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DispensePage {

  private WebDriver driver;

  @FindBy(how = How.XPATH, using = "//*[@class='display-4 font-weight-bold']")
  private WebElement lblDispense;

  public DispensePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public boolean islblDispenseExist() throws GenericAutomationException {
    WebActions.scrollToWebElement(lblDispense,driver);
    return lblDispense.isDisplayed();
  }

}
