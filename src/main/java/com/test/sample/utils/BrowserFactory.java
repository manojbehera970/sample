package com.test.sample.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.test.sample.BrowserType;
import com.test.sample.Config;
import com.test.sample.Constants;



public class BrowserFactory {
	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	// public WebDriver driver = browserFactory();
	// public static WebDriver driver = new ChromeDriver();
	/*
	 * Factory method for getting browsers
	 */
	public static WebDriver getBrowser(BrowserType browserName) {
		WebDriver driver = null;
		// System.setProperty("webdriver.gecko.driver","G:\\eclipse-workspace\\zeeui\\driver\\geckodriver.exe");
		// String browserType=Config.getValue("BROWSER_TYPE");
		if (browserName.equals(BrowserType.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", Constants.firefoxDriver);
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.dir", Config.getValue("EXPORT_FILE_PATH"));
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/excel,application/pdf,application/octet-stream");
			driver = new FirefoxDriver(profile);
			drivers.put("firefox", driver);
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.chromeDriver);
			Map<String, String> prefs = new Hashtable<String, String>();
			prefs.put("download.prompt_for_download", "false");
			prefs.put("download.default_directory", Config.getValue("EXPORT_FILE_PATH"));
			prefs.put("download.extensions_to_open",
					"image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,application/x-excel,application/x-msexcel,application/excel,application/pdf,application/octet-stream");

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			drivers.put("chrome", driver);
		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", Config.getValue("IE_DRIVER"));
			driver = new InternetExplorerDriver();
			drivers.put("ie", driver);
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", Config.getValue("EDGE_DRIVER"));
			driver = new EdgeDriver();
			drivers.put("edge", driver);
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
			drivers.put("safari", driver);
		} else if (browserName.equals("htmlUnit")) {
			driver = new HtmlUnitDriver();
		}

		return driver;

	}
	public static void closeAllDriver() {
		for (String key : drivers.keySet()) {
//			drivers.get(key).close();
			drivers.get(key).quit();
		}
	}
}
