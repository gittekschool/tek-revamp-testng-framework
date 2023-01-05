package tek.framework.test.api;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.framework.api.modal.TokenResponse;

public class GetAccountTest extends APIBaseTests {
	
	@BeforeMethod
	public void initiateTest() {
		RestAssured.baseURI = "https://tek-insurance-api.azurewebsites.net";
	}
	
	@Test
	public void getAccountHappyPath() {
		TokenResponse token = getToken("supervisor", "tek_supervisor");
		RequestSpecification request = getRequest();
		
		// Adding Authorization Header
		request.header("Authorization" , "Bearer " + token.getToken());
		request.param("primaryPersonId", 2001);
		
		Response response=request.get("/api/accounts/get-account");
		Assert.assertEquals(response.getStatusCode(), 200);
		
		int actualId = response.getBody().jsonPath().get("primaryPerson.id");
		Assert.assertEquals(actualId, 2001);
		
		String actualEmail = response.getBody().jsonPath().get("primaryPerson.email");
		Assert.assertEquals(actualEmail, "mohammad11@tekschool.us");
		//Using json Path you can access Entities in Response Objects 
		String accountType = response.getBody().jsonPath().get("primaryPerson.user.accountType");
		Assert.assertEquals(accountType, "CUSTOMER");
	}
}
