package tek.framework.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowser implements Browser{

	@Override
	public WebDriver getBrowser() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	}

}
