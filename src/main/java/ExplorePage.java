

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

	
	@FindBy(xpath = "//h3[text()='No businesses found']")
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
		try {
			element.clear(shopSearchField);
		} catch (Exception e) {
		}
		try {
			element.clear(placeSearchField);
		} catch (Exception e) {
		}
	}

	public boolean verifyValidShopDetails(String expectedShop) {
		try {
		
		WebElement shopEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
		By.xpath("//*[normalize-space()='" + expectedShop + "']")
		));
		return shopEl.isDisplayed();
		} catch (Exception e) {
		return false;
		}
		}

		public boolean verifyNoBusinessesFound() {
		try {
		
		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement msg = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
		By.xpath("//h3[contains(text(),'No businesses found')]")
		));
		return msg.isDisplayed();
		} catch (Exception e) {
		return false;
		}
		}

		public boolean verifyPlaceResult(String expected) {
		try {
		WebElement placeEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
		By.xpath("//*[contains(text(),'" + expected + "')]")
		));
		return placeEl.isDisplayed();
		} catch (Exception e) {
		return false;
		}
		}

		public void selectCategory(String category) {
		element.Click(categoryDropdownBtn);
		
		WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(
		By.xpath("//*[normalize-space()='" + category + "']")
		));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryOption);
		
		}

		public void selectSortBy(String sortBy) {
		element.Click(sortByDropdownBtn);
		WebElement sortItem = wait.until(ExpectedConditions.elementToBeClickable(
		By.xpath("//*[normalize-space()='" + sortBy + "']")
		));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", sortItem);
		
		}

		public void selectMinRating(String rating) {
			WebElement dropdownBtn = wait.until(ExpectedConditions.elementToBeClickable(minRatingDropdownBtn));
			try {
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownBtn);
			} catch (Exception e) {
			dropdownBtn.click();
			}

			WebElement ratingItem = wait.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//*[normalize-space()='" + rating + "']")
			));
			try {
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", ratingItem);
			} catch (Exception e) {
			ratingItem.click();
			}
			}
        
		public boolean areFilteredResultsDisplayed() {
		return element.isDisplayed(businessResultsHeading);
		}
		}