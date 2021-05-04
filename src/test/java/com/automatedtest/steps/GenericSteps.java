package com.automatedtest.steps;

import DataProvider.WorkClassHeroDataProvider;
import Exceptions.GenericAutomationException;
import Models.ReliefSummary;
import Models.WorkClassHero;
import Pages.HomePage;
import Services.OppenheimerService;
import Utils.DriverFactoryManager;
import Utils.GenericCache;
import Utils.IGenericCache;
import Utils.PropertyManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class GenericSteps {

  WorkClassHero workClassHero;
  List<WorkClassHero> workClassHeroes;
  WebDriver driver;
  IGenericCache cache = new GenericCache();

  @Given("user prepare single record for insertion")
  public void user_prepare_single_record_for_insertion() {
    workClassHero = WorkClassHeroDataProvider.getSingleWorkClassHero();
    fetchCountOfWorkClassHeros();
  }

  @When("user trigger single insertion api")
  public void user_trigger_single_insertion_api() {
    OppenheimerService.addWorkClassHero(workClassHero);
  }

  @Then("user can see single working class hero available in system")
  public void user_can_see_single_working_class_hero_available_in_system() {
    ReliefSummary currentReliefSummary = OppenheimerService.getTaxReliefSummary();
    ReliefSummary previousReliefSummary = (ReliefSummary) cache.get("oldreliefSummary").get();
    Integer currentCount = Integer.parseInt(currentReliefSummary.getTotalWorkingClassHeroes());
    Integer previousCount = Integer.parseInt(previousReliefSummary.getTotalWorkingClassHeroes());
    Assert.assertTrue("one record must be inserted", (currentCount - previousCount == 1));

  }

  @Given("user prepare multiple record for insertion")
  public void user_prepare_multiple_record_for_insertion(DataTable table) {
    workClassHeroes = WorkClassHeroDataProvider.getMultipleWorkClassHero(table);
    fetchCountOfWorkClassHeros();
  }

  @When("user trigger multiple insertion api")
  public void user_trigger_multiple_insertion_api() {
    OppenheimerService.addListOfWorkClassHero(workClassHeroes);
  }

  @Then("user can see multiple working class hero's available in system")
  public void user_can_see_multiples_working_class_hero_available_in_system() {
    ReliefSummary currentReliefSummary = OppenheimerService.getTaxReliefSummary();
    ReliefSummary previousReliefSummary = (ReliefSummary) cache.get("oldreliefSummary").get();
    Integer currentCount = Integer.parseInt(currentReliefSummary.getTotalWorkingClassHeroes());
    Integer previousCount = Integer.parseInt(previousReliefSummary.getTotalWorkingClassHeroes());
    Assert.assertTrue("more than one record must be inserted", ((currentCount - previousCount) > 1));
  }

  @Given("user open the home page")
  public void user_open_homepage() {
    driver = DriverFactoryManager.getWebDriver();
    driver.get(PropertyManager.applicationURL);
  }

  @Given("user fetch already available work class hero count")
  public void user_open_homepage_count_fetch() {
    fetchCountOfWorkClassHeros();
  }

  @When("user bulk upload csv for list of working class heros")
  public void user_trigger_bulk_insertion_api() throws Exception {
    HomePage homePage = new HomePage(driver);
    homePage.clickOnChooseFile();
    Runtime.getRuntime().exec(
        "rundll32 url.dll,FileProtocolHandler " + System.getProperty("user.dir")
            + "\\src\\main\\resources\\upload.exe");
    Thread.sleep(5000);
  }

  @Then("user can see bulk working class hero's available in system")
  public void user_can_see_bulk_working_class_hero_available_in_system() {
    ReliefSummary currentReliefSummary = OppenheimerService.getTaxReliefSummary();
    ReliefSummary previousReliefSummary = (ReliefSummary) cache.get("oldreliefSummary").get();
    Integer currentCount = Integer.parseInt(currentReliefSummary.getTotalWorkingClassHeroes());
    Integer previousCount = Integer.parseInt(previousReliefSummary.getTotalWorkingClassHeroes());
    Assert.assertTrue("some records must be inserted via bulk upload", ((currentCount - previousCount) > 0));
  }

  public void fetchCountOfWorkClassHeros(){
    ReliefSummary reliefSummary = OppenheimerService.getTaxReliefSummary();
    cache.put("oldreliefSummary", reliefSummary);
  }

}
