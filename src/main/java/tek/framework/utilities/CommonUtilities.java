package tek.framework.utilities;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tek.framework.base.BaseSetup;

public class CommonUtilities extends BaseSetup {
	
	private WebDriverWait getWait() {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
	}
	
	public void elementClick(WebElement element) {
		getWait().until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	public String getElementText(WebElement element) {
		return getWait().until(ExpectedConditions.visibilityOf(element)).getText();
	}
	
	public void sentTextToElement(WebElement element, String value) {
		getWait().until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
	}
	

}
