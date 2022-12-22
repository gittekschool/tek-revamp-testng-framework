package tek.framework.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tek.framework.utilities.CommonUtilities;

public class CSRLoginPage extends CommonUtilities{
	
	public CSRLoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	//POM File with @FindBy Annotation. 
	@FindBy(name = "username")
	public WebElement usernameInput; 
	
	@FindBy(name = "password")
	public WebElement passwordInput;
	
	@FindBy(id = "loginButton")
	public WebElement loginButton; 
	
	@FindBy(xpath = "//app-banner/div")
	public WebElement bannerMessage; 
	
	
	public void doLogin(String username, String password) {
		sentTextToElement(usernameInput, username);
		sentTextToElement(passwordInput, password);
		elementClick(loginButton);
	}
	
	
	public String getBannerText() {
		return getElementText(bannerMessage);
	}

}
