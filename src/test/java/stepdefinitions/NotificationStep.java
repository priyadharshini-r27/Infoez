package stepdefinitions;

import org.testng.Assert;
import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NotificationPage;

public class NotificationStep {
	private NotificationPage notificationsPage;

	private NotificationPage getNotificationsPage() {
	if (notificationsPage == null) {
	notificationsPage = new NotificationPage(DriverFactory.getDriver()); 
	}
	return notificationsPage;
	}

	
	@And("user selects Notifications option from the profile dropdown menu")
	public void user_selects_Notification_option_from_the_profile_dropdown_menu() throws InterruptedException {
		getNotificationsPage().clickNotificationsOption();
	}

	@Then("user is redirected to the Notifications page with URL containing {string}")
	public void user_is_redirected_to_the_notifications_page_with_url_containing(String expectedUrlFraction) {
		Assert.assertTrue(getNotificationsPage().isNotificationsPageLoaded(expectedUrlFraction),
				"Failed to load Notifications page or URL did not contain: " + expectedUrlFraction);
	}

	@And("the Notifications page main heading should be displayed")
	public void the_notifications_page_main_heading_should_be_displayed() {
		Assert.assertTrue(getNotificationsPage().isMainHeadingDisplayed(),
				"Notifications page main heading is not displayed!");
	}

	
}
