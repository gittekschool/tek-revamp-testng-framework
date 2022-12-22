package tek.framework.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeBrowser implements Browser{

	@Override
	public WebDriver getBrowser() {
		WebDriverManager.edgedriver().setup();
		
		return new EdgeDriver();
	}
	
	

}
