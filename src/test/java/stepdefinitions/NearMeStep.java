package stepdefinitions;

import java.util.Properties;
import org.testng.Assert;
import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NearMePage;
import utils.ConfigReader;

public class NearMeStep {
	private NearMePage nearMePage;
	private Properties prop = new ConfigReader().init_prop();

	private NearMePage getNearMePage() {
		if (nearMePage == null) {
			nearMePage = new NearMePage(DriverFactory.getDriver());
		}
		return nearMePage;
	}

	@Given("user is on the Infoez home landing page")
	public void user_is_on_the_infoez_home_landing_page() {
		DriverFactory.getDriver().get(prop.getProperty("url"));
	}

	@When("user clicks on the Near me navigation link")
	public void user_clicks_on_the_near_me_navigation_link() {
		getNearMePage().clickNearMeNavLink();
	}

	@Then("user should be redirected to the Near Me page with URL containing {string}")
	public void user_should_be_redirected_to_the_near_me_page_with_url_containing(String expectedUrlFraction) {
		Assert.assertTrue(getNearMePage().isNearMePageLoaded(expectedUrlFraction),
				"Failed to load Near Me page or URL did not contain: " + expectedUrlFraction);
	}

	@And("the Near Me page should display heading {string}")
	public void the_near_me_page_should_display_heading(String expectedHeading) {
		Assert.assertTrue(getNearMePage().isHeadingDisplayed(), "Heading 'Businesses on map' is not displayed!");
	}

	@When("user enters search nearby keyword {string} in the search box")
	public void user_enters_search_nearby_keyword_in_the_search_box(String keyword) {
		getNearMePage().enterSearchNearby(keyword);
	}

	@And("clicks on Apply filter button")
	public void clicks_on_apply_filter_button() {
		getNearMePage().clickApplyButton();
	}

	@Then("map search results should be successfully displayed")
	public void map_search_results_should_be_successfully_displayed() {
		Assert.assertTrue(getNearMePage().areResultsDisplayed(), "Map search results section is not displayed!");
	}
}
