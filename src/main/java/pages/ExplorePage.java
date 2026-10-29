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

public class ExplorePage {
private WebDriver driver;
private Element element;
private WebDriverWait wait;

@FindBy(xpath = "//a[text()='Explore']")
private WebElement exploreModuleLink;

@FindBy(xpath = "//input[@data-testid='search-query-input']")
private WebElement shopSearchField;

@FindBy(xpath = "//input[@data-testid='search-location-input']")
private WebElement placeSearchField;

@FindBy(xpath = "//h3[contains(text(),'No businesses found')]")
private WebElement noBusinessesFoundMsg;

@FindBy(xpath = "//button[@data-testid='filter-category']")
private WebElement categoryDropdownBtn;

@FindBy(xpath = "//button[@data-testid='filter-sort']")
private WebElement sortByDropdownBtn;

@FindBy(xpath = "//button[@data-testid='filter-min-rating']")
private WebElement minRatingDropdownBtn;

@FindBy(xpath = "//h1[text()='Businesses']")
private WebElement businessResultsHeading;

public ExplorePage(WebDriver driver) {
this.driver = driver;
this.element = new Element(driver);
this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
PageFactory.initElements(driver, this);
}

public void clickExploreModule() {
element.Click(exploreModuleLink);
}

public void enterShopName(String shopName) {
element.clear(shopSearchField);
element.sendKeys(shopSearchField, shopName);
shopSearchField.sendKeys(Keys.ENTER);
}

public void enterPlace(String place) {
element.clear(placeSearchField);
element.sendKeys(placeSearchField, place);
placeSearchField.sendKeys(Keys.ENTER);
}

public void clearSearchField() {
element.clear(shopSearchField);
element.clear(placeSearchField);
}

public boolean verifyValidShopDetails(String expectedShop) {
WebElement shopEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
By.xpath("//*[normalize-space()='" + expectedShop + "']")
));
return shopEl.isDisplayed();
}

public boolean verifyNoBusinessesFound() {
WebElement msg = wait.until(ExpectedConditions.visibilityOf(noBusinessesFoundMsg));
return msg.isDisplayed();
}

public boolean verifyPlaceResult(String expected) {
WebElement placeEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
By.xpath("//span[normalize-space()='" + expected + "']")
));
return placeEl.isDisplayed();
}

public void selectCategory(String category) {
element.Click(categoryDropdownBtn);
WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(
By.xpath("//span[normalize-space()='" + category + "']")
));
((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryOption);
}

public void selectSortBy(String sortBy) {
element.Click(sortByDropdownBtn);
WebElement sortItem = wait.until(ExpectedConditions.elementToBeClickable(
By.xpath("//span[normalize-space()='" + sortBy + "']")
));
((JavascriptExecutor) driver).executeScript("arguments[0].click();", sortItem);
}

public void selectMinRating(String rating) {
WebElement dropdownBtn = wait.until(ExpectedConditions.elementToBeClickable(minRatingDropdownBtn));
((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownBtn);

WebElement ratingItem = wait.until(ExpectedConditions.elementToBeClickable(
By.xpath("//*[normalize-space()='" + rating + "']")
));
((JavascriptExecutor) driver).executeScript("arguments[0].click();", ratingItem);
}

public boolean areFilteredResultsDisplayed() {
return element.isDisplayed(businessResultsHeading);
}
}