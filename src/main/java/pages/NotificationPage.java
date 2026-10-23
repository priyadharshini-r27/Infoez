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

public class NotificationPage {
	private WebDriver driver;
	private Element element; 

	
	public NotificationPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	this.element = new Element(driver); 
	}

	@FindBy(xpath = "//button[@data-testid='user-menu-btn']")
	private WebElement profileMenuTrigger;

	@FindBy(xpath = "//div[@data-testid='menu-profile']")
	private WebElement dropdownProfileItem;

	@FindBy(xpath = "//div[@data-testid='menu-notifications']")
	private WebElement notificationsDropdownOption;

	@FindBy(xpath = "//h1[text()='Notifications']")
	private WebElement notificationsMainHeading;



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

	public void clickNotificationsOption() throws InterruptedException {
		Thread.sleep(2000);
		element.waitForVisibility(notificationsDropdownOption);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", notificationsDropdownOption);
	}

	public boolean isNotificationsPageLoaded(String expectedUrlFraction) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			return wait.until(ExpectedConditions.urlContains(expectedUrlFraction));
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isMainHeadingDisplayed() {
		return element.isDisplayed(notificationsMainHeading);
	}

	public String getMainHeadingText() {
		return element.getText(notificationsMainHeading);
	}

}
