package stepdefinitions;



	import org.testng.Assert;
	import driverFactory.DriverFactory;
	import io.cucumber.java.en.And;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import pages.MyEnquiriesPage;

	public class MyEnquiriesStep {
	 private MyEnquiriesPage myEnquiriesPage;

	 private MyEnquiriesPage getMyEnquiriesPage() {
	 if (myEnquiriesPage == null) {
	 myEnquiriesPage = new MyEnquiriesPage(DriverFactory.getDriver());
	 }
	 return myEnquiriesPage;
	 }

	
	 @And("user selects {string} option from the profile dropdown")
	 public void user_selects_option_from_the_profile_dropdown(String optionName) {
	 getMyEnquiriesPage().clickMyEnquiriesOption();
	 }

	 @Then("user is redirected to the My Enquiries page with URL containing {string}")
	 public void user_is_redirected_to_the_my_enquiries_page_with_url_containing(String expectedUrlFraction) {
	 Assert.assertTrue(getMyEnquiriesPage().isMyEnquiriesPageLoaded(expectedUrlFraction),
	 "Failed to load My Enquiries page or URL did not contain: " + expectedUrlFraction);
	 }

	 @And("the My Enquiries page displays subheading {string} and main heading {string}")
	 public void the_my_enquiries_page_displays_subheading_and_main_heading(String expectedSubHeading, String expectedMainHeading) {
	 Assert.assertEquals(getMyEnquiriesPage().getSubHeadingText(), expectedSubHeading, "Subheading text mismatch!");
	 Assert.assertEquals(getMyEnquiriesPage().getMainHeadingText(), expectedMainHeading, "Main heading text mismatch!");
	 }

	 @And("the enquiry card displayed")
	 public void the_enquiry_card_displayed() {
	 Assert.assertTrue(getMyEnquiriesPage().isEnquiryCardDisplayed(),
	 "Enquiry card is not displayed on the My Enquiries page!");
	 }
	}

