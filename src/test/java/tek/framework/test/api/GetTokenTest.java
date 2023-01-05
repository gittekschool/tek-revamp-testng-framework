package tek.framework.test.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTokenTest {
	
	
	/*
	 * Scenario Get Authenticated with Valid user 
	 * valid password store the token 
	 * validate the status code. 
	 * validate the Username in the response. 
	 */
	
	@Test
	public void getTokenHappyPathTest() {
		//Tell RestAssured what is the Base URI. 
		RestAssured.baseURI = "https://tek-insurance-api.azurewebsites.net";
		
		//Given: Prepare request specification for this API. 
		RequestSpecification request = RestAssured.given();
		//Add Content Type to Request as Application/JSON
		request.contentType(ContentType.JSON);
		
		//Add Body to the request. 
		//Request body can be String. can be Map or Can be an Encapsulated class. 
		//Note: If request body required to be an Array of Object you can Use List.
		Map<String, String> tokenRequestMap = new HashMap<>();
		tokenRequestMap.put("username", "Supervisor");
		tokenRequestMap.put("password", "tek_supervisor");
		
		request.body(tokenRequestMap);
		
		//This Test is POST request and get request. 
		Response response = request.post("/api/token");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.asPrettyString());
		
		String usernameActual = response.getBody().jsonPath().get("username");
		Assert.assertEquals(usernameActual, "Supervisor".toUpperCase());
		
		String token = response.getBody().jsonPath().get("token");
		Assert.assertNotNull(token);
		Assert.assertFalse(token.isEmpty());
	} 

}
