package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class NewStepDefinition extends Utils {

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;

	@Given("URL and Query Parameters")
	public void url_and_query_parameters() throws IOException {

		requestSpec = given().spec(requestSpecification());

		System.out.println("Given");
	}

	@When("user calls movieListAPI with GET http request")
	public void user_calls_movie_list_api_with_get_http_request() {
		System.out.println("When");

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = requestSpec.when().get("resource/yitu-d5am.json");
	}

	@Then("status in response body is OK")
	public void status_in_response_body_is_ok() {
		assertEquals(response.getStatusCode(), 200);
	}

}
