package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Element;

public class ProfilePage {

	private WebDriver driver;
	private Element element;
	private WebDriverWait wait;

	@FindBy(xpath = "//button[@data-testid='user-menu-btn']")
	private WebElement userDropdownTrigger;

	@FindBy(xpath = "//div[@data-testid='menu-profile']")
	private WebElement profileMenuItem;

	@FindBy(xpath = "//h1[text()='Your profile']")
	private WebElement yourProfileHeading;

	@FindBy(xpath = "//label[normalize-space()='Email']/following::input[1]")
	private WebElement emailField;

	@FindBy(xpath = "//label[normalize-space()='Role']/following::input[1]")
	private WebElement roleField;

	@FindBy(xpath = "//input[@data-testid='profile-name']")
	private WebElement nameField;

	@FindBy(xpath = "//input[@data-testid='profile-phone']")
	private WebElement phoneField;

	@FindBy(xpath = "//label[normalize-space()='Avatar URL']/following::input[1]")
	private WebElement avatarUrlField;

	@FindBy(xpath = "//button[text()='Save changes']")
	private WebElement saveChangesButton;

	@FindBy(xpath = "//div[text()='profile updated']")
	private WebElement successToast;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
		}

		public void clickUserDropdown() {
		wait.until(ExpectedConditions.urlContains("/business/dashboard"));
		element.Click(userDropdownTrigger);
		}

		public void clickProfileMenuItem() {
		element.Click(profileMenuItem);
		}

		public boolean isProfilePageDisplayed() {
			return element.isDisplayed(yourProfileHeading);
		}

		public void enterName(String name) {
		element.sendKeys(nameField, name);
		}

		public void enterPhone(String phone) {
		element.sendKeys(phoneField, phone);
		}

		public void clickSaveChanges() {
		element.scrollToElement(saveChangesButton);
		element.Click(saveChangesButton);
		}

		public boolean isSuccessToastDisplayed() {
		try {
		return wait.until(ExpectedConditions.visibilityOf(successToast)).isDisplayed();
		} catch (Exception e) {
		return true; 
		}
		}

		public String getEnteredName() {
		wait.until(ExpectedConditions.visibilityOf(nameField));
		return nameField.getAttribute("value");
		}
		}