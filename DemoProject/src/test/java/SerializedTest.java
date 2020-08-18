import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import Pojo.AddNewPlace;
import Pojo.Location;
import files.Payload;


public class SerializedTest {
	
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
		
		
		
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	Response res = given().log().all().queryParam("key", "qaclick")
	.body(p)
	.when().post("maps/api/place/add/json")
	.then().assertThat().statusCode(200).extract().response();
	
	String responseString = res.asString();
	System.out.println(responseString);
	
}
}
