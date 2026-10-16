package driverFactory;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<>();

	public WebDriver init_driver(String browser) {
		System.out.println("browser value is:" + browser);
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--remote-debugging-pipe");

			java.util.Map<String, Object> prefs = new java.util.HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.default_content_setting_values.notifications", 2);
			chromeOptions.setExperimentalOption("prefs", prefs);

			chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			chromeOptions.addArguments("--disable-save-password-bubble");
			chromeOptions.setExperimentalOption("prefs",
					Map.of("credentials_enable_service", false, "profile.password_manager_enabled", false));
			tldriver.set(new ChromeDriver(chromeOptions));
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver());
		} else if (browser.equals("safari")) {
			tldriver.set(new SafariDriver());
		} else {
			System.out.println("please pass the correct browser value:" + browser);
		}

		return getDriver();

	}

	public static WebDriver getDriver() {
		return tldriver.get();
	}

	public void configBrowser() {

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

	}

	public static void unload() {

		tldriver.remove();

	}

}
