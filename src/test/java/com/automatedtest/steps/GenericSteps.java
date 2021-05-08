package com.automatedtest.steps;

import DataProvider.WorkClassHeroDataProvider;
import Models.ReliefSummary;
import Models.TaxRelief;
import Models.WorkClassHero;
import Pages.DispensePage;
import Pages.HomePage;
import Services.OppenheimerService;
import Utils.CommonFunctions;
import Utils.DriverFactoryManager;
import Utils.GenericCache;
import Utils.IGenericCache;
import Utils.PropertyManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class GenericSteps {

  WorkClassHero workClassHero;
  List<WorkClassHero> workClassHeroes;
  WebDriver driver;
  IGenericCache cache = new GenericCache();
  List<TaxRelief> taxReliefList;
  HomePage homePage;

  @Given("user prepare single record for insertion")
  public void user_prepare_single_record_for_insertion() throws Exception {
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
    Assert
        .assertTrue("more than one record must be inserted", ((currentCount - previousCount) > 1));
  }

  @Given("user open the home page")
  public void user_open_homepage() {
    driver = DriverFactoryManager.getWebDriver();
    driver.get(PropertyManager.applicationURL);
  }

  @Given("user fetch already available work class hero count")
  public void user_open_homepage_count_fetch() throws Exception {
    fetchCountOfWorkClassHeros();
    List<WorkClassHero> workClassHeroListFromBatch = CommonFunctions.recordBatchFile(
        System.getProperty("user.dir") + "\\src\\main\\resources\\Workhero.csv");
    List<WorkClassHero> existingRecords = WorkClassHeroDataProvider.recordedWorkClassHeros;
    workClassHeroListFromBatch.forEach(we -> existingRecords.add(we));
    WorkClassHeroDataProvider.recordedWorkClassHeros = existingRecords;
  }

  @When("user bulk upload csv for list of working class heros")
  public void user_trigger_bulk_insertion_api() throws Exception {
    homePage = new HomePage(driver);
    homePage.clickOnChooseFile();
    Runtime.getRuntime().exec( System.getProperty("user.dir")
        + "\\src\\main\\resources\\upload.exe " + System.getProperty("user.dir")+"\\src\\main\\resources\\Workhero.csv");
    Thread.sleep(5000);
  }

  @Then("user can see bulk working class hero's available in system")
  public void user_can_see_bulk_working_class_hero_available_in_system() {
    ReliefSummary currentReliefSummary = OppenheimerService.getTaxReliefSummary();
    ReliefSummary previousReliefSummary = (ReliefSummary) cache.get("oldreliefSummary").get();
    Integer currentCount = Integer.parseInt(currentReliefSummary.getTotalWorkingClassHeroes());
    Integer previousCount = Integer.parseInt(previousReliefSummary.getTotalWorkingClassHeroes());
    Assert.assertTrue("some records must be inserted via bulk upload",
        ((currentCount - previousCount) > 0));
  }

  public void fetchCountOfWorkClassHeros() {
    ReliefSummary reliefSummary = OppenheimerService.getTaxReliefSummary();
    cache.put("oldreliefSummary", reliefSummary);
  }


  @Given("user should collect existing working class hero in system")
  public void user_should_collect_existing_working_class_hero_in_system() {
    WorkClassHeroDataProvider.recordedWorkClassHeros.forEach(System.out::println);
  }

  @When("user fetch tax relief of each working class hero from system")
  public void user_fetch_tax_relief_of_each_working_class_hero_in_system() {
    taxReliefList = OppenheimerService.getListOfTaxRelief();
  }

  @Then("user can report tax relief of each working clas hero")
  public void user_can_report_tax_relief_of_each_working_clas_hero() {
    List<TaxRelief> taxReliefListExpected = CommonFunctions
        .getTaxReliefFromWorkClassHeros(WorkClassHeroDataProvider.recordedWorkClassHeros);
    Assert.assertTrue("Expected tax relief and actual values not matching ",
        taxReliefListExpected.equals(taxReliefListExpected));
  }

  @When("user click on dispense button")
  public void user_click_on_dispense_button() throws Exception {
    homePage = new HomePage(driver);
    homePage.clickDispenseBtn();
  }

  @Then("user can see the successfull dispension")
  public void user_can_see_the_successfull_dispension() throws Exception {
    DispensePage dispensePage = new DispensePage(driver);
    Assert.assertTrue("tax relief successfully dispensed", dispensePage.islblDispenseExist());
  }

}
