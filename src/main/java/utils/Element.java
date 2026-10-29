package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

public class Element {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public Element(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constant.EXPLICIT_WAIT_TIME));

	}

	public WebElement waitForClick(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitForVisibility(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public List<WebElement> waitForVisibilityOfAllelemens(List<WebElement> element) {
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public Alert waitForalertisPresent() {
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void Click(WebElement element) {
		WebElement WebElement = waitForClick(element);
		WebElement.click();
	}

	public void sendKeys(WebElement element, String value) {
		WebElement WebElement = waitForVisibility(element);
		WebElement.click();
		WebElement.clear();
		WebElement.sendKeys(value);
	}
	
	public void clear(WebElement element) {
		WebElement webElement = waitForVisibility(element);
		webElement.clear();
	}

	public int getSize(List<WebElement> elements) {
		List<WebElement> webElements = waitForVisibilityOfAllelemens(elements);
		return webElements.size();
	}

	public String getText(WebElement element) {
		WebElement webElement = waitForVisibility(element);
		return webElement.getText();
	}

	public String getAttribute(WebElement element, String attributename) {
		WebElement webElement = waitForVisibility(element);
		return webElement.getAttribute(attributename);

	}

	public void selectByvalue(WebElement element, String value) {
		WebElement webElement = waitForVisibility(element);
		Select select = new Select(webElement);
		select.selectByValue(value);
	}

	public void selectByIntex(WebElement element, int index) {
		WebElement webElement = waitForVisibility(element);
		Select select = new Select(webElement);
		select.selectByIndex(index);
	}

	public void selectByText(WebElement element, String value) {
		WebElement webElement = waitForVisibility(element);
		Select select = new Select(webElement);
		select.selectByVisibleText(value);
	}

	public String getTittle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isDisplayed(WebElement element) {
		WebElement webElement = waitForVisibility(element);
		return element.isDisplayed();
	}
	
	public boolean waitForUrlContains(String urlFraction) {
		try {
			return wait.until(ExpectedConditions.urlContains(urlFraction));
		} catch (Exception e) {
			return false;
		}
	}

	public void jsclick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	}

	public void clickVisibleElement(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		for (WebElement element : elements) {
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				break;
			}
		}

	}

	public void clickElementByText(List<WebElement> elements, String text) {

		for (WebElement element : elements) {

			if (element.isDisplayed() && element.isEnabled()

					&& element.getText().trim().equals(text)) {

				element.click();

				break;

			}

		}

	}

	


}