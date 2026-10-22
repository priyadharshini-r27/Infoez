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
	import pages.FavoritesPage;
	import pages.LoginPage;
	import utils.ConfigReader;

	public class FavoritesStep {

	 private WebDriver driver;
	 private FavoritesPage favoritesPage;
	 private LoginPage loginPage;

	 private FavoritesPage getFavoritesPage() {
	 if (favoritesPage == null) {
	 driver = DriverFactory.getDriver();
	 favoritesPage = new FavoritesPage(driver);
	 }
	 return favoritesPage;
	 }

	 private LoginPage getLoginPage() {
	 if (loginPage == null) {
	 driver = DriverFactory.getDriver();
	 loginPage = new LoginPage(driver);
	 }
	 return loginPage;
	 }

	 @Given("User is on the dashboard page")
	 public void user_is_on_the_dashboard_page() {
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

	 @When("User clicks on the user menu at top right")
	 public void user_clicks_on_the_user_menu_at_top_right() {
	 getFavoritesPage().clickUserDropdown();
	 }

	 @And("User clicks on Favorites from the dropdown menu")
	 public void user_clicks_on_favorites_from_the_dropdown_menu() {
	 getFavoritesPage().clickFavoritesMenuItem();
	 }

	 @Then("User verifies the Favorites page is loaded with heading {string}")
	 public void user_verifies_the_favorites_page_is_loaded_with_heading(String headingText) {
	 Assert.assertTrue(getFavoritesPage().isFavoritesPageLoaded(headingText),
	 "Favorites page did not load with expected heading: " + headingText);
	 }


	}

