package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Element;

public class SubscriptionPage {
	private WebDriver driver;
	private Element element;

	@FindBy(xpath = "//button[@data-testid='user-menu-btn']")
	private WebElement profileMenuTrigger;

	@FindBy(xpath = "//div[@data-testid='menu-profile']")
	private WebElement dropdownProfileItem;

	@FindBy(xpath = "//div[@role='menuitem'][contains(.,'Subscription')]")
	private WebElement subscriptionDropdownOption;

	@FindBy(xpath = "//h1[text()='Choose your plan']")
	private WebElement chooseYourPlanHeading;

	@FindBy(xpath = "//div[@data-testid='plan-free']")
	private WebElement freePlanCard;

	@FindBy(xpath = "//div[@data-testid='plan-starter']")
	private WebElement starterPlanCard;

	@FindBy(xpath = "//div[@data-testid='plan-pro']")
	private WebElement proPlanCard;

	@FindBy(xpath = "//div[@data-testid='plan-enterprise']")
	private WebElement enterprisePlanCard;

	@FindBy(xpath = "//div[text()='Current']")
	private WebElement currentBadge;

	@FindBy(xpath = "//button[text()='Active plan']")
	private WebElement activePlanButton;

	public SubscriptionPage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		PageFactory.initElements(driver, this);
	}

	public void openProfileMenuTrigger() {
		element.waitForVisibility(profileMenuTrigger);
		profileMenuTrigger.click();
          

		try {
			element.waitForVisibility(dropdownProfileItem);
		} catch (Exception e) {
			new Actions(driver).moveToElement(profileMenuTrigger).sendKeys(Keys.ENTER).perform();
			element.waitForVisibility(dropdownProfileItem);
		}
	}

	public void clickSubscriptionOption() {
		element.waitForVisibility(subscriptionDropdownOption);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", subscriptionDropdownOption);
	}

	public boolean isSubscriptionPageLoaded(String expectedUrlFraction) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.urlContains(expectedUrlFraction));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isChooseYourPlanHeadingDisplayed() {
		return element.isDisplayed(chooseYourPlanHeading);
	}

	public String getChooseYourPlanHeadingText() {
		return element.getText(chooseYourPlanHeading);
	}

	public boolean areAllPricingPlansVisible() {
		return element.isDisplayed(freePlanCard) && element.isDisplayed(starterPlanCard)
				&& element.isDisplayed(proPlanCard) && element.isDisplayed(enterprisePlanCard);
	}

	public boolean isProPlanActive() {
		return element.isDisplayed(currentBadge) && element.isDisplayed(activePlanButton);
	}
}
