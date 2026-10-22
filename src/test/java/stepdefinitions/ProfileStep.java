package stepdefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.ProfilePage;
import utils.ConfigReader;

public class ProfileStep {

 private WebDriver driver;
 private ProfilePage profilePage;
 private LoginPage loginPage;

 private ProfilePage getProfilePage() {
 if (profilePage == null) {
 driver = DriverFactory.getDriver();
 profilePage = new ProfilePage(driver);
 }
 return profilePage;
 }

 private LoginPage getLoginPage() {
 if (loginPage == null) {
 driver = DriverFactory.getDriver();
 loginPage = new LoginPage(driver);
 }
 return loginPage;
 }

 @Given("User is on the Business dashboard page")
 public void user_is_on_the_Business_dashboard_page() {
 driver = DriverFactory.getDriver();
 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

 if (!driver.getCurrentUrl().contains("/business/dashboard")) {
 getLoginPage().doLogin(
 ConfigReader.getProperty("validmailid"),
 ConfigReader.getProperty("validpassword")
 );
 wait.until(d -> d.getCurrentUrl().contains("/business/dashboard"));
 }
 }

 @When("User clicks on the profile menu at top right")
 public void user_clicks_on_the_profile_menu_at_top_right() throws InterruptedException {
 getProfilePage().clickUserDropdown();
 }

 @And("User clicks on Profile from the dropdown menu")
 public void user_clicks_on_profile_from_the_dropdown_menu() throws InterruptedException {
 getProfilePage().clickProfileMenuItem();
 }

 @Then("User verifies the Profile page is loaded with heading {string}")
 public void user_verifies_the_profile_page_is_loaded_with_heading(String headingText) {
 Assert.assertTrue(getProfilePage().isProfilePageDisplayed(headingText),
 "Profile page did not load with expected heading: " + headingText);
 }

 @When("User updates the profile name to {string}")
 public void user_updates_the_profile_name_to(String name) {
 getProfilePage().enterName(name);
 }

 @And("User updates the profile phone to {string}")
 public void user_updates_the_profile_phone_to(String phone) {
 getProfilePage().enterPhone(phone);
 }

 @And("User clicks on Save changes button")
 public void user_clicks_on_save_changes_button() {
 getProfilePage().clickSaveChanges();
 }

 @Then("A profile update success alert should be displayed")
 public void a_profile_update_success_alert_should_be_displayed() {
 Assert.assertTrue(getProfilePage().isSuccessToastDisplayed(),
 "Success notification was not displayed after clicking save.");
 }

 @And("The profile name input should have value {string}")
 public void the_profile_name_input_should_have_value(String expectedName) {
 Assert.assertEquals(getProfilePage().getEnteredName(), expectedName,
 "Profile name attribute value did not match.");
 }
}