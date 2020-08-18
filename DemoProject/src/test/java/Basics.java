import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import files.Payload;
public class Basics  {
	
	//Given All Input details
	//When submitting the API with resource name  and POST method 
	//Then validate the response. 
	
	@Test
	void add_Place() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.addNewPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo ("APP")).extract().response().asString();
		
		System.out.println("The response from the POST API is "+response);
		
		JsonPath js = new JsonPath(response);
		
		String place_id = js.getString("place_id");
		System.out.println(place_id);
		
		//Update Place
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\"70 Summer walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).log().all().body("msg", equalTo( "70 Summer walk, USA"));
	}

	
}
