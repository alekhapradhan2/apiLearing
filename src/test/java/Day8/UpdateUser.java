package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
public class UpdateUser {
	
	@Test
	void test_Updateuser(ITestContext context)
	{
		String token=(String) context.getSuite().getAttribute("BearerToken");
		int id=(Integer)context.getSuite().getAttribute("User_Id");
		Faker faker=new Faker();
		String fullName=faker.name().fullName();
		String mailId=faker.internet().emailAddress();
		
		JSONObject data=new JSONObject();
		
		data.put("name", fullName);
		data.put("email", mailId);
		data.put("gender", "Male");
		data.put("status", "Inactive");
		
		given()
			.header("Authorization","Bearer "+token)
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all()
			.body("name",equalTo(fullName))
			.body("email", equalTo(mailId));
	
	}

}
