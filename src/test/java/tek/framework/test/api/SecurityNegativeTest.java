package tek.framework.test.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.framework.api.modal.TokenRequest;

public class SecurityNegativeTest {

	/*
	 * Scenario: Call /api/token end point with invalid username and password.
	 * validate the status code, validate the error message.
	 */
	@BeforeMethod
	public void initiateApiTest() {
		// Base URI for all the Test methods in this Class.
		RestAssured.baseURI = "https://tek-insurance-api.azurewebsites.net";
	}

	@Test
	public void apiTokenTestWithInvalidUsername() {

		// Prepare request Specification.
		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);

		// Prepare request body
		Map<String, String> tokenRequestMap = new HashMap<>();
		tokenRequestMap.put("username", "Supervisor1234");
		tokenRequestMap.put("password", "tek_supervisor");

		request.body(tokenRequestMap);
		// Post request to EndPoint
		Response response = request.post("/api/token");
		Assert.assertEquals(response.getStatusCode(), 400);

		String errorMessageActual = response.getBody().jsonPath().get("errorMessage");

		Assert.assertEquals(errorMessageActual, "User not found");

	}

	@Test
	public void apiTokenWithInvalidPassword() {
		TokenRequest tokenRequest = new TokenRequest("supervisor", "supervisor121341");
		// Prepare Request.
		RequestSpecification request = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(tokenRequest);
		
		Response response =request.post("/api/token");
		
		Assert.assertEquals(response.getStatusCode(), 400);
		
		String errorMessageActual=response.getBody().jsonPath().get("errorMessage");
		
		Assert.assertEquals(errorMessageActual, "Password Not Matched");
		
	}
}
