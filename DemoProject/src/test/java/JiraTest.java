import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class JiraTest {

	@Test
	
	public void AddComment(){
		
		// Get Session Info
		RestAssured.baseURI = "http://localhost:9090";
		SessionFilter session = new SessionFilter();
		
		String response = given().header("Content-Type", "application/json")
		.body("{ \"username\": \"raghudanen\", \"password\": \"Gnusmas1!\" }")
		.filter(session)
		.log().all()
		.when()
		.post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
	String expectedmessage = "Last fresh Hi How are you?";
		//Add comment
		
		String addCommentResponse = given().pathParam("Issue_id", "10019").log().all().header("Content-Type", "application/json")
		.body("{\r\n" + 
				"    \"body\": \" "+expectedmessage+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.filter(session)
		.when()
		.post("/rest/api/2/issue/{Issue_id}/comment")
		.then().assertThat().statusCode(201)
		.log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(addCommentResponse);
		String commendId = js.getString("id");
		
		// Add attachment
		
		given().header("X-Atlassian-Token", "no-check" ).pathParam("Issue_id", "10019")
		.header("Content-Type", "multipart/form-data")
		.filter(session)
		.multiPart("file", new File("JIRA.txt")).log().all()
		.when().post("/rest/api/2/issue/{Issue_id}/attachments")
		
		.then().log().all().assertThat().statusCode(200);
		
		//Get Issue	
		
		String issueDetails = given().header("Content-Type", "application/json")
		.filter(session)
		.pathParam("Issue_id2", "10019")
		.queryParam("fields", "comment")
		.when().get("rest/api/2/issue/{Issue_id2}")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		
		
		JsonPath js2 = new JsonPath(issueDetails);
		int commentsCount = js2.getInt("fields.comment.comments.size()");
		for (int i=0; i < commentsCount; i++) {
			String commentidIssue = js2.get("fields.comment.comments["+i+"].id").toString();
			if (commentidIssue.equalsIgnoreCase(commendId)) {
				String message = js2.get("fields.comment.comments["+i+"].body").toString().trim();
				System.out.println(message);
				Assert.assertEquals(message, expectedmessage);
				
			}
		}
		
	}
	
	
}
