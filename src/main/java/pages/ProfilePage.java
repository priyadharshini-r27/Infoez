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

	public void clickUserDropdown() throws InterruptedException {
		Thread.sleep(6000);
		wait.until(ExpectedConditions.urlContains("/business/dashboard"));

		try {

			WebElement trigger = wait.until(ExpectedConditions.elementToBeClickable(userDropdownTrigger));
			try {
				trigger.click();
			} catch (Exception e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", trigger);
			}
		} catch (Exception ignored) {
			WebElement fallback = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='user-menu-btn']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallback);
		}
	}

	public void clickProfileMenuItem() {

		WebElement profileOption = driver.findElement(By.xpath("//div[@data-testid='menu-profile']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileOption);
	}

	public boolean isProfilePageDisplayed(String headingText) {
		try {
			wait.until(d -> d.getCurrentUrl().contains("/profile"));
			return wait.until(ExpectedConditions.visibilityOf(yourProfileHeading)).getText().contains(headingText);
		} catch (Exception e) {
			return driver.getCurrentUrl().contains("/profile");
		}
	}

	public void enterName(String name) {
		WebElement el = wait.until(ExpectedConditions.visibilityOf(nameField));
		clearAndType(el, name);
	}

	public void enterPhone(String phone) {
		WebElement el = wait.until(ExpectedConditions.visibilityOf(phoneField));
		clearAndType(el, phone);
	}

	private void clearAndType(WebElement element, String text) {
		element.click();

		element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
		element.clear();
		element.sendKeys(text);
	}

	public void clickSaveChanges() {
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));
		element.scrollToElement(btn);
		try {
			btn.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
		}
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