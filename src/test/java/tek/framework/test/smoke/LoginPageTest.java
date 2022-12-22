package tek.framework.test.smoke;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tek.framework.test.pages.CSRHomePage;
import tek.framework.test.pages.CSRLoginPage;
import tek.framework.utilities.CommonUtilities;

public class LoginPageTest extends CommonUtilities {

	// TestNG Class life Cylce.
	// Before & After.
	// BeforeSuite AfterSuite
	// BeforeClass AfterClass
	// BeforeTest AfterTest
	// BeforeMethod AfterMethod

	// BeforeMethod always run before each Method that have @Test annotation.
	@BeforeMethod
	public void testInitilized() {
		System.out.println("Test Start Here");
		// Open Browser and Navigate to URL
		super.openBrowser();
	}
	
	@Test
	public void loginTestPositive() throws InterruptedException {
		// Enter Username
		CSRLoginPage loginPage = new CSRLoginPage();
		
		sentTextToElement(loginPage.usernameInput, "supervisor");
		//enter password
		sentTextToElement(loginPage.passwordInput, "tek_supervisor");
		// click on login button. 
		elementClick(loginPage.loginButton);
		
		Thread.sleep(2000);
		
		CSRHomePage homePage = new CSRHomePage(); 
		String homePageTitle = getElementText(homePage.homePageTitle);
		Assert.assertEquals(homePageTitle, "Tek Insurance App");
		
		String profileName = getElementText(homePage.homePageProfileName);
		profileName = profileName.substring(0, profileName.indexOf("account_circle")).trim();
		Assert.assertEquals(profileName, "Supervisor");
	}
	
	@Test(dataProvider = "invalidTestData")
	public void loginTestNegative(String username, String password, String expectedMessage) {
		// Login 
		// Wrong username , valid password
		// invalid username , invalid password. 
		// valid username , invalid password. 
		CSRLoginPage loginPage = new CSRLoginPage(); 
		loginPage.doLogin(username, password);
		String bannerMessage = loginPage.getBannerText();
		
		Assert.assertEquals(bannerMessage, expectedMessage);
	}
	
	@DataProvider(name = "invalidTestData")
	private String[][] invalidTestData() {
		String [][] testData = {
				{"Wrong Username", "wrong password" , "User not found"},
				{"Wrong username", "tek_supervisor" , "User not found"},
				{"supervisor" , "wrong password" , "Password Not Matched"}
		};
		
		return testData; 
	}
	
	@AfterMethod
	public void closeTest() {
		System.out.println("Test End Here");
		super.quiteDriver();
	}

}
