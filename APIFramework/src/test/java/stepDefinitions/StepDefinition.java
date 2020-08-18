package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import pojo.AddNewPlace;
import pojo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification respec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_Id;
	
	//JsonPath js;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
				
		
//		  resspec = new ResponseSpecBuilder() .expectStatusCode(200)
//		  .expectContentType(ContentType.JSON) .build();
		 


		//	RestAssured.baseURI = "https://rahulshettyacademy.com";
		res = given().spec(requestSpecification())
					.body(data.addPlacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		resourceAPI.getResource();
		System.out.println(resourceAPI.getResource());
		respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
				//.then().spec(respec).extract().response();
	}
	@Then("the API call is successfully completed with status code {int}")
	public void the_api_call_is_successfully_completed_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		// Write code here that turns the phrase above into concrete actions
//	String resp = response.asString();
//	js = new JsonPath(resp);
	assertEquals(getJsonPath(response, keyValue), ExpectedValue);
	}

	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	  
	 place_Id = getJsonPath(response, "place_id"); 
	  
	  res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
	//  System.out.println("The place ID is "+place_Id);
	  user_calls_with_http_request(resource , "GET"); 
	  String actualName =   getJsonPath(response, "name"); 
	  System.out.println(actualName);
	  assertEquals(actualName, expectedName); 
	  }
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
	}


}
