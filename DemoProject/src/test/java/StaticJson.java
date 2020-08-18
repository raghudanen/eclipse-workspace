import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	@Test()
	public void AddBook() throws IOException {
		
		System.out.println("AddBook");
		
		RestAssured.baseURI = "http://216.10.245.166/";
		String response = given().header("Content-Type", "application/json").
		body(GenerateStringFromResource("C:\\Users\\91988\\Desktop\\Interview material from Rahul\\AddBookDetails.json")).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
