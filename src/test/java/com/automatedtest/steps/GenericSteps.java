package com.automatedtest.steps;

import Models.WorkClassHero;
import Services.OppenheimerService;
import Utils.DriverFactoryManager;
import Utils.PropertyManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class GenericSteps {


  @Given("user navigates to home page")
  public void user_navigates_to_home_page() {
//        WebDriver driver = DriverFactoryManager.getWebDriver();
//        driver.get(PropertyManager.applicationURL);
//        driver.close();
//        DriverFactoryManager.quitDriver();
  }

  @When("do some action")
  public void do_some_action() {
//    OppenheimerService oppenheimerService = new OppenheimerService();
//    WorkClassHero workClassHero = new WorkClassHero();
//    workClassHero.setBirthday("10 oct 2020");
//    workClassHero.setGender("M");
//    workClassHero.setName("abc");
//    workClassHero.setNatId("123");
//    workClassHero.setSalary("1000");
//    workClassHero.setTax("100");
//    oppenheimerService.addWorkClassHero(workClassHero);
//    List<WorkClassHero> workClassHeroList = new ArrayList<>();
//    workClassHeroList.add(workClassHero);
//    oppenheimerService.addListOfWorkClassHero(workClassHeroList);

  }

  @Then("user can see the result")
  public void user_can_see_the_result() {
  }

}
