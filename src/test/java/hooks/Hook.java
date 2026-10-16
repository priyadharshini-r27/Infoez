package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;

public class Hook {

	private DriverFactory driverFactory;
	private WebDriver driver;

	@Before(order = 0)
	public void setupDriver() {

		ConfigReader.init_prop();

		String browserName = ConfigReader.getProperty("browser");
		String appUrl = ConfigReader.getProperty("url");

		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
		driver.get(appUrl);
	}

	@After(order = 0)
	public void tearDown(Scenario scenario) {
		WebDriver currentDriver = DriverFactory.getDriver();
		if (currentDriver != null) {
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Failed_Screenshot");
			}
			currentDriver.quit();
			DriverFactory.unload();
		}
	}
}