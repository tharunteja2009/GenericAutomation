package Hooks;

import Utils.DriverFactoryManager;
import Utils.WindowsUtils;
import io.cucumber.java.After;

public class Hooks {

  @After
  public void afterScenario(){
    DriverFactoryManager.getWebDriver().quit();
    DriverFactoryManager.quitDriver();
    WindowsUtils.killProcess("chromedriver.exe");
    WindowsUtils.killProcess("upload.exe");
  }

}
