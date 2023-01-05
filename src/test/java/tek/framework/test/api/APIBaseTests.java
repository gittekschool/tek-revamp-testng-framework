package tek.framework.test.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import tek.framework.api.modal.TokenRequest;
import tek.framework.api.modal.TokenResponse;

public class APIBaseTests {

	public RequestSpecification getRequest() {
		return RestAssured.given().contentType(ContentType.JSON);
	}
	
	public TokenResponse getToken(String username, String password) {
		TokenRequest tokenRequest = new TokenRequest(username, password);
		RequestSpecification request = getRequest();
		request.body(tokenRequest);
		
		Response response = request.post("/api/token");
		
		//using As and Encapsulated Class you can convert the body to Java Object. 
		TokenResponse responseBody = response.getBody().as(TokenResponse.class);
		return responseBody;
	}

}
