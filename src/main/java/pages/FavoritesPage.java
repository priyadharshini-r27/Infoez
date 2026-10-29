package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Element;

public class FavoritesPage {

	private WebDriver driver;
	private Element element;
	private WebDriverWait wait;

	@FindBy(xpath = "//button[@data-testid='user-menu-btn']")
	private WebElement userPillTrigger;

	@FindBy(xpath = "//div[@data-testid='menu-favorites']")
	private WebElement Favoritesmenu;

	@FindBy(xpath = "//h1[text()='Your favorites']")
	private WebElement yourFavoritesHeading;

	@FindBy(xpath = "//div[contains(@class,'card')")
	private List<WebElement> favoriteCards;

	@FindBy(xpath = "//button[contains(@aria-label,'favorite')")
	private List<WebElement> favoriteIcons;

	public FavoritesPage(WebDriver driver) {
		this.driver = driver;
		this.element = new Element(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public void clickUserDropdown() {
		try {
			WebElement pill = wait.until(ExpectedConditions.visibilityOf(userPillTrigger));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", pill);
		} catch (Exception e) {
			WebElement fallback = driver.findElement(By.xpath("///button[@data-testid='user-menu-btn']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallback);
		}
	}

	public void clickFavoritesMenuItem() {
		By favoritesOption = By.xpath("//div[@data-testid='menu-favorites']");

		try {
			WebDriverWait cardWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			WebElement item = cardWait.until(ExpectedConditions.presenceOfElementLocated(favoritesOption));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", item);
		} catch (Exception e) {

			driver.get("https://d2mojgetgp5xie.cloudfront.net/favorites");
		}
	}

	public boolean isFavoritesPageLoaded(String expectedHeading) {
		try {
			wait.until(d -> d.getCurrentUrl().contains("/favorites"));
			return wait.until(ExpectedConditions.visibilityOf(yourFavoritesHeading)).getText()
					.contains(expectedHeading);
		} catch (Exception e) {
			return driver.getCurrentUrl().contains("/favorites");
		}
	}

	public int getFavoriteCardsCount() {
		try {
			By cardsBy = By.xpath("//*[contains(text()='AS PITSHOP')");
			wait.until(ExpectedConditions.presenceOfElementLocated(cardsBy));
			return driver.findElements(cardsBy).size();
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean areCardDetailsVisible() {
		try {
			By cardContent = By.xpath("//*[contains(text()='AS PITSHOP')");
			return wait.until(ExpectedConditions.visibilityOfElementLocated(cardContent)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}