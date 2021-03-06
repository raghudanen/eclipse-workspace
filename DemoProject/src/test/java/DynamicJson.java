
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.nio.file.Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void AddBook(String ISBN ,String Aisle) {
		
		System.out.println("AddBook");
		
		RestAssured.baseURI = "http://216.10.245.166/";
		String response = given().header("Content-Type", "application/json").
		body(Payload.addBook(ISBN, Aisle)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	@DataProvider(name= "BooksData")
	public Object[][] GetData() {
		return new Object[][] {{"bade", "9222"}, {"rbfc", "5631"}, {"drgs", "5522"}};
	}
	
	@Test(dataProvider="BooksData")
	public void DeleteBook(String ISBN ,String Aisle) {
		
		System.out.println("DeleteBook");
		
		RestAssured.baseURI = "http://216.10.245.166/";
		String response = given().header("Content-Type", "application/json").
		body(Payload.deleteBookpayload(ISBN, Aisle)).
		when().post("Library/DeleteBook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		
		//JsonPath js = new JsonPath(response);
		//String id = js.get("ID");
		//System.out.println(id);
	}
	



}
