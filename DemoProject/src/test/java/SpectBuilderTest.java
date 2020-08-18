import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import Pojo.AddNewPlace;
import Pojo.Location;
import files.Payload;


public class SpectBuilderTest {
	
	@Test 
	public void TestSerialization(){
		
		
		AddNewPlace p = new AddNewPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		List <String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();

		ResponseSpecification resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		
//	RestAssured.baseURI = "https://rahulshettyacademy.com";
	RequestSpecification res = given().spec(req).log().all().body(p);
	
	Response response = res.when().post("maps/api/place/add/json")
	.then().spec(resspec).extract().response();
	
	String responseString = response.asString();
	System.out.println(responseString);
	
}
}
