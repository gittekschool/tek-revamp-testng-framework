package tek.framework.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tek.framework.utilities.CommonUtilities;

public class CSRHomePage extends CommonUtilities {
	
	public CSRHomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath = "//mat-toolbar/span[1]")
	public WebElement homePageTitle; 
	

	@FindBy(xpath = "//mat-toolbar/button[2]/span[1]")
	public WebElement homePageProfileName; 
}
