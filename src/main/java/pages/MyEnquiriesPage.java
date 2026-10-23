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

	public class MyEnquiriesPage {
	private WebDriver driver;
	private Element element;

	
	@FindBy(xpath = "//button[@data-testid='user-menu-btn']")
	private WebElement profileMenuTrigger;

	
	@FindBy(xpath = "//div[@data-testid='menu-profile']")
	private WebElement dropdownProfileItem;

	
	@FindBy(xpath = "//div[text()='My Enquiries']")
	private WebElement myEnquiriesDropdownOption;

	
	@FindBy(xpath = "//div[text()='Sent']")
	private WebElement sentSubHeading;

	@FindBy(xpath = "//h1[text()='Your enquiries']")
	private WebElement yourEnquiriesMainHeading;

	
	@FindBy(xpath = "//*[normalize-space()='Aroma Cafe']")
	private WebElement firstEnquiryCard;

	public MyEnquiriesPage(WebDriver driver) {
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

	public void clickMyEnquiriesOption() {
	element.waitForVisibility(myEnquiriesDropdownOption);
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", myEnquiriesDropdownOption);
	}

	public boolean isMyEnquiriesPageLoaded(String expectedUrlFraction) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.urlContains(expectedUrlFraction));
			} catch (Exception e) {
			return false;
			}
			}
	

	public String getSubHeadingText() {
	return element.getText(sentSubHeading);
	}

	public String getMainHeadingText() {
	return element.getText(yourEnquiriesMainHeading);
	}

	public boolean isEnquiryCardDisplayed() {
	return element.isDisplayed(firstEnquiryCard);
	}
	}

