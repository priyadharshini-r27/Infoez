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

 @FindBy(xpath = "//h1[text()='Business dashboard']")
 private WebElement dashboardHeading;

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

 @FindBy(xpath = "//button[contains(text(),'Edit')]")
 private WebElement editButton;

 @FindBy(xpath = "//input[@data-testid='biz-name']")
 private WebElement BusinessNameField;

 @FindBy(xpath = "//button[contains(text(),'Save')]")
 private WebElement saveButton;

 @FindBy(xpath = "//a[@data-testid='biz-nav-leads']")
 private WebElement leadsMenu;

 @FindBy(xpath = "//h1[text()='Leads']")
 private WebElement leadsHeading;

 public MyBusinessesPage(WebDriver driver) {
 this.driver = driver;
 this.element = new Element(driver);
 this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
 PageFactory.initElements(driver, this);
 }

 public String getPageTitle() {
 return element.getText(dashboardHeading);
 }

 public boolean isDashboardDisplayed() {
 return wait.until(ExpectedConditions.visibilityOf(dashboardHeading)).isDisplayed();
 }

 public void clickMyBusinessesButton() {
 element.Click(myBusinessesMenu);
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

 public void clickEditButton() {
 element.Click(editButton);
 }

 public void enterEditBusinessName(String businessName) {
 wait.until(ExpectedConditions.visibilityOf(BusinessNameField));
 BusinessNameField.clear();
 BusinessNameField.sendKeys(businessName);
 }

 public void clickSaveChanges() {
 element.Click(saveButton);
 }

 public void clickLeadsButton() {
 element.Click(leadsMenu);
 }

 public boolean isLeadsModuleDisplayed() {
 return wait.until(ExpectedConditions.visibilityOf(leadsHeading)).isDisplayed();
 }

 public boolean isBusinessPresentInList(String businessName) {
 WebElement businessElement = wait.until(
 ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + businessName + "']"))
 );
 return businessElement.isDisplayed();
 }
}