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

	@Then("user should navigate to home page {string} and {string}")
	public void user_should_navigate_to_home_page(String expectedResult, String message) {
		if (expectedResult.equalsIgnoreCase("valid")) {
			Assert.assertTrue(loginPage.isDashboardDisplayed(),
					"Login failed: Dashboard was not displayed for valid credentials.");
		} else if (expectedResult.equalsIgnoreCase("invalid")) {
			Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
					"Error message not displayed for invalid credentials.");
		}
		if (expectedResult.equalsIgnoreCase("failed")) {
			String actualMessage = loginPage.getEmailValidationMessage();
			System.out.println("Expected Message:" + message);
			System.out.println("Actual Message:" + message);

			Assert.assertEquals(actualMessage.trim(), message.trim(), "invalid lodin mismatch");
		} else if (expectedResult.equalsIgnoreCase("empty")) {
			String actualMessage;
			if (message.equalsIgnoreCase("Email")) {
				actualMessage = loginPage.getEmailValidationMessage();
			} else if (message.equalsIgnoreCase("Password")) {
				actualMessage = loginPage.getpasswordValidationMessage();

			} else {
				throw new IllegalArgumentException("Unknown field:" + message);
			}
			System.out.println("validation Message:" + actualMessage);
		
			Assert.assertNotNull(actualMessage, message + "validation message null");

			Assert.assertFalse(actualMessage.isEmpty(), message + "valiation message is empty");
		}

	}
}