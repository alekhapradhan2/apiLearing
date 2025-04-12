package ApiTesting;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Practice {
	
	//@Test
	void validateResponseCode()
	{
		given()
		
		.when()
			.get("https://jsonplaceholder.typicode.com/posts")
		.then()
			.statusCode(200);
	}
	
	
	
	//Validate JSON response for a GET request by checking if the title of the first post is not empty.
	
	//@Test
	void validateJsonBody()
	{
		given()
					
					.when()
						.get("https://jsonplaceholder.typicode.com/posts")
					.then()
						.body("[0].title", not(emptyString()));
		
//		String title=res.jsonPath().get("[0].title").toString();
//		Assert.assertFalse(title.isEmpty());		
					
	}
	
	//Send a POST request to create a new resource with a JSON body and verify the response contains the same data.
	
	String id;
//	@Test(priority = 1)
	void sendPostrequestAndValidateThat()
	{
		HashMap datas=new HashMap();
		datas.put("userId", 20);
		datas.put("title", "Ganguram");
		datas.put("body", "All types of sweets available");
		
		
	Response res=	given()
			.contentType(ContentType.JSON)
			.body(datas)
		.when()
			.post("https://jsonplaceholder.typicode.com/posts");
//		.then()
//			.body("userId", equalTo(20))
//			.body("title", equalTo("Ganguram"))
//			.body("body", equalTo("All types of sweets available"))
//			.log().all();
		id=res.jsonPath().get("id").toString();
		System.out.println(id);
		Assert.assertEquals(res.jsonPath().get("userId").toString(), "20");
		Assert.assertEquals(res.jsonPath().get("title").toString(), "Ganguram");
		Assert.assertEquals(res.jsonPath().get("body").toString(), "All types of sweets available");
		
		
		
		System.out.println(res.getBody().asPrettyString());
   			
	}
	//Send a PUT request to update a resource and verify the response reflects the updated values.
	//@Test(priority = 2)
	void updateTheRecord()
	{
		HashMap datas=new HashMap();
		datas.put("userId", 20);
		datas.put("title", "Alekha");
		datas.put("body", "All types of sweets available");
		Response res=	given()
				.contentType("application/json")
				.body(datas)
			.when()
				.put("https://jsonplaceholder.typicode.com/posts/32");
//		
//		Assert.assertEquals(res.jsonPath().get("userId").toString(), "20");
//		Assert.assertEquals(res.jsonPath().get("title").toString(), "Alekha");
//		Assert.assertEquals(res.jsonPath().get("body").toString(), "All types of sweets available");
		System.out.println(res.getBody().asPrettyString());
		System.out.println(res.statusCode());
	}
	
	//Send a DELETE request and verify that the status code is 200 or 204.
	
	@Test(timeOut =5000 )
	void deleteTheRecord()
	{
		Response res=given()
		
		.when()
			.delete("https://jsonplaceholder.typicode.com/posts/32");
		
		int statusCode=res.statusCode();
		System.out.println(statusCode);
				
	}

}
