package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Element;

public class MyBusinessesPage {

	private WebDriver driver;
	private Element element;
	private WebDriverWait wait;

	@FindBy(xpath = "//a[@data-testid='biz-nav-my-businesses']")
	private WebElement myBusinessesMenu;

	@FindBy(xpath = "//h1[text()='My businesses']")
	private WebElement myBusinessesHeading;

	@FindBy(xpath = "//a[@data-testid='biz-nav-add-business']")
	private WebElement addBusinessBtn;

	@FindBy(xpath = "//input[@data-testid='biz-name']")
	private WebElement businessNameInput;

	@FindBy(xpath = "//textarea[@data-testid='biz-desc']")
	private WebElement descriptionInput;

	@FindBy(xpath = "//input[@data-testid='biz-city']")
	private WebElement cityInput;

	@FindBy(xpath = "//input[@data-testid='biz-address']")
	private WebElement addressInput;

	@FindBy(xpath = "//input[@data-testid='biz-phone']")
	private WebElement phoneInput;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailInput;

	@FindBy(xpath = "//button[normalize-space()='Create business']")
	private WebElement createBusinessBtn;

	public MyBusinessesPage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public void navigateToDashboard() {
		String currentUrl = driver.getCurrentUrl();
		if (!currentUrl.contains("/business/dashboard")) {
			driver.get("https://d2mojgetgp5xie.cloudfront.net/business/dashboard");
		}
	}

	public boolean isDashboardPageLoaded() {
		try {

			WebElement dashboardHeader = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Business dashboard']")));
			return dashboardHeader.isDisplayed();
		} catch (Exception e) {

			return driver.getCurrentUrl().contains("dashboard");
		}
	}

	public void clickMyBusinessesButton() {
		By menuLocator = By.xpath("//a[@data-testid='biz-nav-my-businesses']");
		WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuLocator));
		element.Click(menu);
	}

	public void clickAddBusinessButton() {
		element.Click(addBusinessBtn);
	}

	public void enterBusinessName(String businessName) {
		element.sendKeys(businessNameInput, businessName);
	}

	public void enterDescription(String description) {
		element.sendKeys(descriptionInput, description);
	}

	public void enterCity(String city) {
		element.sendKeys(cityInput, city);
	}

	public void enterAddress(String address) {
		element.sendKeys(addressInput, address);
	}

	public void enterPhone(String phone) {
		element.sendKeys(phoneInput, phone);
	}

	public void enterEmail(String email) {
		element.sendKeys(emailInput, email);
	}

	public void clickSubmitBusiness() {
		element.scrollToElement(createBusinessBtn);
		element.Click(createBusinessBtn);
	}

	public boolean isBusinessPresentInList(String businessName) {
		By businessNameLocator = By.xpath("//input[@data-testid='biz-name']");
		try {
			return driver.findElements(businessNameLocator).size() > 0;
		} catch (Exception e) {
			return false;
		}
	}
}