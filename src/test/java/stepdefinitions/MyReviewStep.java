package stepdefinitions;


	import org.testng.Assert;
	import driverFactory.DriverFactory;
	import io.cucumber.java.en.And;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import pages.MyReviewPage;

	public class MyReviewStep {
	 private MyReviewPage myReviewsPage;

	 private MyReviewPage getMyReviewsPage() {
	 if (myReviewsPage == null) {
	 myReviewsPage = new MyReviewPage(DriverFactory.getDriver());
	 }
	 return myReviewsPage;
	 }

	

	 @When("user clicks on {string} option in the profile dropdown")
	 public void user_clicks_on_option_in_the_profile_dropdown(String optionName) {
	 getMyReviewsPage().clickMyReviewsOption();
	 }

	 @Then("user is redirected to the My Reviews page with URL containing {string}")
	 public void user_is_redirected_to_the_my_reviews_page_with_url_containing(String expectedUrlFraction) {
	 Assert.assertTrue(getMyReviewsPage().isMyReviewsPageLoaded(expectedUrlFraction),
	 "Failed to redirect to My Reviews page or URL did not contain: " + expectedUrlFraction);
	 }

	 @And("the My Reviews page displays subheading {string} and main heading {string}")
	 public void the_my_reviews_page_displays_subheading_and_main_heading(String expectedSubHeading, String expectedMainHeading) {
	 Assert.assertEquals(getMyReviewsPage().getSubHeadingText(), expectedSubHeading, "Subheading mismatch!");
	 Assert.assertEquals(getMyReviewsPage().getMainHeadingText(), expectedMainHeading, "Main heading mismatch!");
	 }

	
	 @And("the review card displays business name")
	 public void the_review_card_displays_business_name() {
	 Assert.assertTrue(getMyReviewsPage().isBusinessNameDisplayed(),
	 "Business name is not displayed on the review card!");
	 }

	 
	 @And("the review card displays business name {string}")
	 public void the_review_card_displays_business_name_with_value(String expectedBusinessName) {
	 Assert.assertTrue(getMyReviewsPage().isBusinessNameDisplayed(),
	 "Business name is not displayed on the review card!");
	 Assert.assertEquals(getMyReviewsPage().getBusinessNameText(), expectedBusinessName,
	 "Review card business name mismatch!");
	 }
	}

