package pages;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import utils.Element;

	public class CreateAccountPage {

	private WebDriver driver;
	private Element element;

	
	@FindBy(xpath = "//a[text()='Create one']")
	private WebElement createOneLink;

	@FindBy(xpath = "//button[@data-testid='role-business']")
	private WebElement businessAccountOption;

	@FindBy(xpath = "//input[@data-testid='register-name']")
	private WebElement fullNameInput;

	@FindBy(xpath = "//input[@data-testid='register-email']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@data-testid='register-phone']")
	private WebElement phoneInput;

	@FindBy(xpath = "//input[@data-testid='register-password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[text()='Create account']")
	private WebElement createAccountButton;

	public CreateAccountPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	this.element = new Element(driver);
	}

	public void clickCreateOneLink() {
	element.clickOnElement(createOneLink);
	}

	public void selectBusinessOption() {
	element.clickOnElement(businessAccountOption);
	}

	public void enterFullName(String fullName) {
	element.typeTextIntoElement(fullNameInput, fullName);
	}

	public void enterEmail(String email) {
	element.typeTextIntoElement(emailInput, email);
	}

	public void enterPhone(String phone) {
	element.typeTextIntoElement(phoneInput, phone);
	}

	public void enterPassword(String password) {
	element.typeTextIntoElement(passwordInput, password);
	}

	public void clickCreateAccountButton() {
	element.clickOnElement(createAccountButton);
	}

	public boolean isCreateAccountButtonDisplayed() {
	return element.isElementDisplayed(createAccountButton);
	}
	public String getFullNameValue() {
		return fullNameInput.getAttribute("value");
		}

		public String getEmailValue() {
		return emailInput.getAttribute("value");
		}

		public String getPhoneValue() {
		return phoneInput.getAttribute("value");
		}

		public String getPasswordValue() {
		return passwordInput.getAttribute("value");
		}
	}

