package stepdefinitions;

import java.util.Properties;
import org.testng.Assert;
import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ExplorePage;
import utils.ConfigReader;

public class ExploreStep {
 private ExplorePage explorePage;
 private Properties prop = new ConfigReader().init_prop();

 private ExplorePage getExplorePage() {
 if (explorePage == null) {
 explorePage = new ExplorePage(DriverFactory.getDriver());
 }
 return explorePage;
 }

 @Given("user is on the Explore Page")
 public void user_is_on_the_explore_page() {
 DriverFactory.getDriver().get(prop.getProperty("url"));
 }

 @When("Business owner click the Explore module")
 public void business_owner_click_the_explore_module() {
 getExplorePage().clickExploreModule();
 }

 @And("Business owner enter {string} in shop search field")
 public void business_owner_enter_in_shop_search_field(String shopName) {
 getExplorePage().enterShopName(shopName);
 }

 @Then("verify the {string} valid {string} details")
 public void verify_the_valid_details(String status, String expected) {
 if (status.equalsIgnoreCase("valid")) {
 Assert.assertTrue(getExplorePage().verifyValidShopDetails(expected),
 "Valid shop details not found for: " + expected);
 } else {
 Assert.assertTrue(getExplorePage().verifyNoBusinessesFound(),
 "Expected 'No businesses found' message was not displayed!");
 }
 }

 @When("Business owner clear the search field in Explore module")
 public void business_owner_clear_the_search_field_in_explore_module() {
 getExplorePage().clearSearchField();
 }

 @And("Business owner enter the shop place {string} search field")
 public void business_owner_enter_the_shop_place_search_field(String place) {
 getExplorePage().enterPlace(place);
 }

 @Then("Business owner should see shop from place {string} with {string} and expected {string}")
 public void business_owner_should_see_shop_from_place_with_and_expected(String place, String status,
 String expected) {
 if (status.equalsIgnoreCase("valid")) {
 Assert.assertTrue(getExplorePage().verifyPlaceResult(expected),
 "Shop for place " + place + " not found!");
 } else {
 Assert.assertTrue(getExplorePage().verifyNoBusinessesFound(),
 "Expected 'No businesses found' message was not displayed!");
 }
 }

 @When("user selects category filter {string}")
 public void user_selects_category_filter(String category)  {
	
 getExplorePage().selectCategory(category);
 }

 @And("user selects sort by filter {string}")
 public void user_selects_sort_by_filter(String sortBy) {
 getExplorePage().selectSortBy(sortBy);
 }

 @And("user selects minimum rating filter {string}")
 public void user_selects_minimum_rating_filter(String rating) {
 getExplorePage().selectMinRating(rating);
 }

 @Then("filtered business listings should be displayed successfully")
 public void filtered_business_listings_should_be_displayed_successfully() {
 Assert.assertTrue(getExplorePage().areFilteredResultsDisplayed(),
 "Filtered business listings heading is not displayed!");
 }
}