package com.automatedtest.steps;

import Utils.DriverFactoryManager;
import Utils.PropertyManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class GenericSteps {


    @Given("user navigates to home page")
    public void user_navigates_to_home_page() {
        WebDriver driver = DriverFactoryManager.getWebDriver();
        driver.get(PropertyManager.applicationURL);
        driver.close();
        DriverFactoryManager.quitDriver();
    }

    @When("do some action")
    public void do_some_action() {

    }

    @Then("user can see the result")
    public void user_can_see_the_result() {
    }

}
