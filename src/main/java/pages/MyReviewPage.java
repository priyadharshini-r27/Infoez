package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Element;

public class MyReviewPage {
	private WebDriver driver;
	private Element element;

	@FindBy(xpath = "//button[@data-testid='user-menu-btn']")
	private WebElement dropdownMenuContainer;

	@FindBy(xpath = "//div[@data-testid='menu-reviews']")
	private WebElement myReviewsDropdownOption;

	@FindBy(xpath = "//div[text()='Contributions']")
	private WebElement contributionsSubHeading;

	@FindBy(xpath = "//h1[text()='Your reviews']")
	private WebElement yourReviewsMainHeading;

	@FindBy(xpath = "//a[text()='Aroma Cafe']")
	private WebElement reviewCardBusinessName;

	public MyReviewPage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickMyReviewsOption() {
		element.waitForVisibility(myReviewsDropdownOption);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", myReviewsDropdownOption);
	}

	public boolean isMyReviewsPageLoaded(String expectedUrlFraction) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.urlContains(expectedUrlFraction));
		} catch (Exception e) {
			return false;
		}
	}

	public String getSubHeadingText() {
		return element.getText(contributionsSubHeading);
	}

	public String getMainHeadingText() {
		return element.getText(yourReviewsMainHeading);
	}

	public boolean isBusinessNameDisplayed() {
		return element.isDisplayed(reviewCardBusinessName);
	}

	public String getBusinessNameText() {
		return element.getText(reviewCardBusinessName);
	}
}
