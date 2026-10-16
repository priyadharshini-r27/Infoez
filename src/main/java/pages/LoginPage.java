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

public class LoginPage {

	private WebDriver driver;
	private Element elementUtil;
	private WebDriverWait wait;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;

	@FindBy(xpath = "//h1[text()='Business dashboard']")
	private WebElement dashboardHeader;

	@FindBy(xpath = "//div[contains(@class,'alert')]")
	private WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.elementUtil = new Element(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void enterEmail(String email) {
		WebElement el = wait.until(ExpectedConditions.visibilityOf(emailInput));
		el.clear();
		el.sendKeys(email);
	}

	public void enterPassword(String password) {
		WebElement el = wait.until(ExpectedConditions.visibilityOf(passwordInput));
		el.clear();
		el.sendKeys(password);
	}

	public void clickSignIn() {
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(signInButton));
		try {
			btn.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
		}
	}

	public void doLogin(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickSignIn();
	}

	public boolean isDashboardDisplayed() {
		try {
			return elementUtil.isDisplayed(dashboardHeader);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isErrorMessageDisplayed() {
		try {
			return elementUtil.isDisplayed(errorMessage);
		} catch (Exception e) {
			return false;
		}
	}
}