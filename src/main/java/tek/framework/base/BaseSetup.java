package tek.framework.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import tek.framework.browsers.ChromeBrowser;
import tek.framework.browsers.EdgeBrowser;
import tek.framework.utilities.FileUtility;

public class BaseSetup {
	
	private static WebDriver driver;
	private Properties properties;

	public BaseSetup() {
		try {
			String filePath = System.getProperty("user.dir") + "/src/main/resources/configs/env_config.properties";
			FileInputStream fis = FileUtility.getFileInputStream(filePath);
			this.properties = new Properties();
			this.properties.load(fis);
		} catch (IOException ex) {
			System.out.println("Exception Happeded " + ex.getMessage());
			throw new RuntimeException("Exception Happeded " + ex.getMessage());
		}
	}

	public void openBrowser() {
		// What is the browser we should run. coming from property files.
		String browser = (String) this.getProperty("ui.browser");
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeBrowser chrome = new ChromeBrowser();
			driver = chrome.getBrowser();
			break;
		case "edge":
			EdgeBrowser edge = new EdgeBrowser();
			driver = edge.getBrowser();
			break;
		default:
			throw new RuntimeException("Unknow browser type");
		}
		// URL should retrieve from property file. 
		String url = this.getProperty("ui.url").toString();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

	private Object getProperty(String key) {
		if (this.properties != null) {
			return this.properties.get(key);
		} else {
			System.out.println("Property File Not Loaded ");
			throw new RuntimeException("Property File Not Loaded ");
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void quiteDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
}
