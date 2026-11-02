package stepdefinitions;


	import org.testng.Assert;
	import org.openqa.selenium.WebDriver;
	import driverFactory.DriverFactory;
	import io.cucumber.java.en.And;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import pages.CreateAccountPage;

	public class CreateAccountStep {

	 private WebDriver driver;
	 private CreateAccountPage createAccountPage;

	 @Given("the user navigates to the Infoez login page")
	 public void the_user_navigates_to_the_infoez_login_page() {
	 driver = DriverFactory.getDriver();
	 createAccountPage = new CreateAccountPage(driver);
	 }

	 @When("the user clicks on the {string} link")
	 public void the_user_clicks_on_the_link(String linkName) {
	 createAccountPage.clickCreateOneLink();
	 }

	 @When("the user selects the {string} account type option")
	 public void the_user_selects_the_account_type_option(String accountType) {
	 createAccountPage.selectBusinessOption();
	 }

	 @And("the user enters the full name {string}")
	 public void the_user_enters_the_full_name(String fullName) {
	 createAccountPage.enterFullName(fullName);
	 }

	 @And("the user enters the email {string}")
	 public void the_user_enters_the_email(String email) {
	 createAccountPage.enterEmail(email);
	 }

	 @And("the user enters the phone number {string}")
	 public void the_user_enters_the_phone_number(String phone) {
	 createAccountPage.enterPhone(phone);
	 }

	 @And("the user enters the password {string}")
	 public void the_user_enters_the_password(String password) {
	 createAccountPage.enterPassword(password);
	 }

	 @And("the user clicks on the {string} button")
	 public void the_user_clicks_on_the_button(String buttonName) {
	 createAccountPage.clickCreateAccountButton();
	 }

	 @Then("the page outcome should match {string}")
	 public void the_page_outcome_should_match(String expectedResult) {
	 if (expectedResult.equalsIgnoreCase("invalid")) {
	 Assert.assertTrue(createAccountPage.isCreateAccountButtonDisplayed());
	 } else if (expectedResult.equalsIgnoreCase("valid")) {
	 Assert.assertFalse(driver.getCurrentUrl().contains("register"));
	 }
	 }
	 @Then("user validates registration input fields")
	 public void userValidatesRegistrationInputFields() {

	 Assert.assertEquals(
	 createAccountPage.getFullNameValue(), "Sam");

	 Assert.assertEquals(
	 createAccountPage.getEmailValue(), "sam@gmail.com");

	 Assert.assertEquals(
	 createAccountPage.getPhoneValue(), "1234567890");

	 Assert.assertFalse(
	 createAccountPage.getPasswordValue().isEmpty(),
	 "Password field is empty");

	 System.out.println("Full Name: " + createAccountPage.getFullNameValue());
	 System.out.println("Email: " + createAccountPage.getEmailValue());
	 System.out.println("Phone: " + createAccountPage.getPhoneValue());
	 System.out.println("Password field is filled");

	 System.out.println("All input fields validated successfully");
	 }
	}

