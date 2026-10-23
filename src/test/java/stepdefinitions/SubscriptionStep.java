package stepdefinitions;



	import org.testng.Assert;
	import driverFactory.DriverFactory;
	import io.cucumber.java.en.And;
	import io.cucumber.java.en.Then;
	import pages.SubscriptionPage;

	public class SubscriptionStep {
	 private SubscriptionPage subscriptionPage;

	 private SubscriptionPage getSubscriptionPage() {
	 if (subscriptionPage == null) {
	 subscriptionPage = new SubscriptionPage(DriverFactory.getDriver());
	 }
	 return subscriptionPage;
	 }

	
	 @And("user selects Subscription option from the profile dropdown menu")
	 public void user_selects_option_from_the_profile_dropdown_menu() {
	 getSubscriptionPage().clickSubscriptionOption();
	 }

	 @Then("user is redirected to the Subscription page with URL containing {string}")
	 public void user_is_redirected_to_the_subscription_page_with_url_containing(String expectedUrlFraction) {
	 Assert.assertTrue(getSubscriptionPage().isSubscriptionPageLoaded(expectedUrlFraction),
	 "Failed to load Subscription page or URL did not contain: " + expectedUrlFraction);
	 }

	 @And("the Subscription page should display pricing heading {string}")
	 public void the_subscription_page_should_display_pricing_heading(String expectedHeading) {
	 Assert.assertTrue(getSubscriptionPage().isChooseYourPlanHeadingDisplayed(),
	 "Pricing heading is not displayed!");
	 Assert.assertEquals(getSubscriptionPage().getChooseYourPlanHeadingText(), expectedHeading,
	 "Pricing heading text mismatch!");
	 }

	 @And("all subscription pricing plans {string}, {string}, {string}, and {string} should be visible")
	 public void all_subscription_pricing_plans_should_be_visible(String p1, String p2, String p3, String p4) {
	 Assert.assertTrue(getSubscriptionPage().areAllPricingPlansVisible(),
	 "One or more pricing plan cards are missing!");
	 }

	 @And("the active plan should be marked as {string} with status {string} and {string} button")
	 public void the_active_plan_should_be_marked_as_with_status_and_button(
	 String planName, String statusBadge, String buttonText) {
	 Assert.assertTrue(getSubscriptionPage().isProPlanActive(),
	 "Active plan indicators ('Current' badge or 'Active plan' button) are missing for " + planName + "!");
	 }
	}

