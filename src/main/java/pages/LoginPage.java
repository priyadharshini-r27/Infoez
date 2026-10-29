package pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utils.Element;

public class LoginPage {

	private WebDriver driver;
	private Element element;
	private WebDriverWait wait;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;

	@FindBy(xpath = "//h1[text()='Business dashboard']")
	private WebElement dashboardHeader;

	@FindBy(xpath = "//div[@data-title and text()='Invalid credentials']")
	WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void enterEmail(String email) {
	    element.sendKeys(emailInput, email);;
		
	}

	public void enterPassword(String password) {
		element.sendKeys(passwordInput, password);
	}

	public void clickSignIn() {
		element.Click(signInButton);
	}

	public void doLogin(String email, String password) {
		enterEmail(email);
		enterPassword(password);
		clickSignIn();
	}

	public boolean isDashboardDisplayed() {
		return element.isDisplayed(dashboardHeader);
	}

	public boolean isErrorMessageDisplayed() {
		return wait.until(
		ExpectedConditions.visibilityOf(errorMessage)
		).isDisplayed();
		
	}
	public String getinvalidMessage() {
		return element.getText(errorMessage);
		
	}
	public String getEmailValidationMessage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].validationMessage;", emailInput);
	}
	public String getpasswordValidationMessage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].validationMessage;", passwordInput);
	}
	
	public void clickWithEmptyEmail() {
		element.clear(emailInput);
		element.Click(signInButton);
		
	}
	public void clickWithEmptypassword() {
		element.clear(passwordInput);
		element.Click(signInButton);
	}
	
	
}
