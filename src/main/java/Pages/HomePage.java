package Pages;

import Exceptions.GenericAutomationException;
import Utils.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  private WebDriver driver;

  @FindBy(how = How.XPATH, using = "//input[@type='file']/..")
  private WebElement btnChooseFile;

  @FindBy(how = How.LINK_TEXT, using = "Dispense Now")
  private WebElement btnDispense;


  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clickOnChooseFile() throws GenericAutomationException {
    WebActions.scrollToWebElement(btnChooseFile,driver);
    if (btnChooseFile.isDisplayed()) {
      btnChooseFile.click();
    } else {
      throw new GenericAutomationException("WebElement btnChooseFile not found");
    }
  }

  public void clickDispenseBtn() throws GenericAutomationException {
    WebActions.scrollToWebElement(btnDispense,driver);
    if (btnDispense.isDisplayed()) {
      btnDispense.click();
    } else {
      throw new GenericAutomationException("WebElement btnDispense not found");
    }
  }

}
