package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Element;

public class NearMePage {
	private WebDriver driver;
	private Element element;
	private WebDriverWait wait;

	
	@FindBy(xpath = "//a[@data-testid='nav-near-me']")
	private WebElement nearMeNavLink;

	
	@FindBy(xpath = "//h1[text()='Businesses on map']")
	private WebElement businessesOnMapHeading;

	
	@FindBy(xpath = "//input[@data-testid='map-search-input']")
	private WebElement searchNearbyInput;

	
	@FindBy(xpath = "//button[text()='Apply']")
	private WebElement applyFilterBtn;

	
	@FindBy(xpath = "//*[contains(text(),'RESULTS')]")
	private WebElement resultsSectionHeading;

	public NearMePage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void clickNearMeNavLink() {
		element.jsclick(nearMeNavLink);
	}

	public boolean isNearMePageLoaded(String expectedUrlFraction) {
		return element.waitForUrlContains(expectedUrlFraction) && element.isDisplayed(businessesOnMapHeading);
	}

	public boolean isHeadingDisplayed() {
		return element.isDisplayed(businessesOnMapHeading);
	}

	public void enterSearchNearby(String keyword) {
		element.sendKeys(searchNearbyInput, keyword);
		searchNearbyInput.sendKeys(Keys.ENTER);
	}

	public void clickApplyButton() {
		element.jsclick(applyFilterBtn);
	}

	public boolean areResultsDisplayed() {
		return element.isDisplayed(resultsSectionHeading);
	}
}
