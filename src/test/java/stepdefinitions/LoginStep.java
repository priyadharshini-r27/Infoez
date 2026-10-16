package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.LoginPage;

public class LoginStep {

 private WebDriver driver;
 private LoginPage loginPage;

 @Given("User is on signin page")
 public void user_is_on_signin_page() {
 driver = DriverFactory.getDriver();
 loginPage = new LoginPage(driver);
 String title = loginPage.getLoginPageTitle();
 Assert.assertNotNull(title, "Login page was not loaded successfully");
 }

 @When("user enters Business Owner email {string}")
 public void user_enters_business_owner_email(String email) {
 loginPage.enterEmail(email);
 }

 @When("User enter password {string}")
 public void user_enter_password(String password) {
 loginPage.enterPassword(password);
 }

 @When("clicks on the Sign in button")
 public void clicks_on_the_sign_in_button() {
 loginPage.clickSignIn();
 }

 @Then("user should navigate to home page {string}")
 public void user_should_navigate_to_home_page(String expectedResult) {
 if (expectedResult.equalsIgnoreCase("valid")) {
 Assert.assertTrue(loginPage.isDashboardDisplayed(), 
 "Login failed: Dashboard was not displayed for valid credentials.");
 } else if (expectedResult.equalsIgnoreCase("invalid")) {
 Assert.assertTrue(loginPage.isErrorMessageDisplayed() || !loginPage.isDashboardDisplayed(), 
 "Expected login failure for invalid credentials, but dashboard was loaded.");
 }
 }
}