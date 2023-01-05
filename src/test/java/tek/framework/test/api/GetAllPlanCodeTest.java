package tek.framework.test.api;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.framework.api.modal.TokenResponse;

public class GetAllPlanCodeTest extends APIBaseTests {

	@BeforeMethod
	public void InitiateTests() {
		RestAssured.baseURI = "https://tek-insurance-api.azurewebsites.net";

	}

	@Test
	public void getAllPlanCodeHappyPathTest() {
		// get a new token to this test.
		TokenResponse tokenResponse = getToken("supervisor", "tek_supervisor");

		// Prepare request Specification.
		RequestSpecification request = getRequest();

		// add the Authorization to Request Header.
		request.header("Authorization", "Bearer " + tokenResponse.getToken());

		Response response = request.get("/api/plans/get-all-plan-code");

		// assert status code.
		Assert.assertEquals(response.getStatusCode(), 200);

		List plans = response.getBody().as(List.class);

		Assert.assertEquals(plans.size(), 4);
	}

	@Test
	public void getPlanCodeNegativeTest() {
		// Prepare request Specification.
		RequestSpecification request = getRequest();

		//Send request without authorization header. 
		Response response =request.get("/api/plans/get-all-plan-code");
		Assert.assertEquals(response.getStatusCode(), 403);
		
	}
	
	//wrong Authorization .
	@Test
	public void getPlanCodeNegativeTestUnauthorized() {
		RequestSpecification request = getRequest();

		//Add Wrong Authorization  
		request.header("Authorization" , "SomeWrongToken");
		Response response =request.get("/api/plans/get-all-plan-code");
		Assert.assertEquals(response.getStatusCode(), 403);
		System.out.println(response.asPrettyString());
	}

}
