package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.MyBusinessesPage;
import utils.ConfigReader;

public class MyBusinessesStep {

 private WebDriver driver;
 private LoginPage loginPage;
 private MyBusinessesPage myBusinessesPage;

 private MyBusinessesPage getMyBusinessesPage() {
 if (myBusinessesPage == null) {
 driver = DriverFactory.getDriver();
 myBusinessesPage = new MyBusinessesPage(driver);
 }
 return myBusinessesPage;
 }

 private LoginPage getLoginPage() {
 if (loginPage == null) {
 driver = DriverFactory.getDriver();
 loginPage = new LoginPage(driver);
 }
 return loginPage;
 }

 
 @When("User clicks on the My Businesses button")
 public void user_clicks_on_the_my_businesses_button() {
 getMyBusinessesPage().clickMyBusinessesButton();
 }

 @When("User clicks on the Add Business button")
 public void user_clicks_on_the_add_business_button() {
 getMyBusinessesPage().clickAddBusinessButton();
 }

 @When("User verify the Add Business navigation")
 public void user_verify_the_add_business_navigation() {
 getMyBusinessesPage().clickAddBusinessButton();
 }

 @And("User enters new business name {string}")
 public void user_enters_new_business_name(String businessName) {
 getMyBusinessesPage().enterBusinessName(businessName);
 }

 @And("User enters new business description {string}")
 public void user_enters_new_business_description(String description) {
 getMyBusinessesPage().enterDescription(description);
 }

 @And("User enters new business city {string}")
 public void user_enters_new_business_city(String city) {
 getMyBusinessesPage().enterCity(city);
 }

 @And("User enters new business address {string}")
 public void user_enters_new_business_address(String address) {
 getMyBusinessesPage().enterAddress(address);
 }

 @And("User enters new business phone {string}")
 public void user_enters_new_business_phone(String phone) {
 getMyBusinessesPage().enterPhone(phone);
 }

 @And("User enters new business email {string}")
 public void user_enters_new_business_email(String email) {
 getMyBusinessesPage().enterEmail(email);
 }

 @And("User clicks on submit new business button")
 public void user_clicks_on_submit_new_business_button() {
 getMyBusinessesPage().clickSubmitBusiness();
 }

 @Then("The new business {string} should be displayed in the businesses list")
 public void the_new_business_should_be_displayed_in_the_businesses_list(String businessName) {
 getMyBusinessesPage().clickMyBusinessesButton();
 Assert.assertTrue(getMyBusinessesPage().isBusinessPresentInList(businessName),
 "Created business '" + businessName + "' was not found in the businesses list.");
 }


 @When("User clicks on the edit button")
 public void user_clicks_on_the_edit_button() {
 getMyBusinessesPage().clickEditButton();
 }

 @When("User enters business name{string}")
 public void user_enters_business_name(String businessName) {
 getMyBusinessesPage().enterEditBusinessName(businessName);
 }

 @When("User enters business description{string}")
 public void user_enters_business_description(String description) {
 getMyBusinessesPage().enterDescription(description);
 }

 @Then("User click on Save changes button")
 public void user_click_on_save_changes_button() {
 getMyBusinessesPage().clickSaveChanges();
 }


 @When("User clicks on the Leads button")
 public void user_clicks_on_the_leads_button() {
 getMyBusinessesPage().clickLeadsButton();
 }

 @Then("User should be navigated to leads module")
 public void user_should_be_navigated_to_leads_module() {
 Assert.assertTrue(getMyBusinessesPage().isLeadsModuleDisplayed(), "User was not successfully navigated to the leads module.");
 }
}