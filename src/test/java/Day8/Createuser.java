package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
public class Createuser {
	
	
	@Test
	void test_createUser(ITestContext context)
	{
		String token="aba5cf17e0567dc43645f4e6321be5170bd481ccd93870a3081f79f9941adb49";
		
		Faker faker=new Faker();
		String fullName=faker.name().fullName();
		String mailId=faker.internet().emailAddress();
		
		JSONObject data=new JSONObject();
		
		data.put("name", fullName);
		data.put("email", mailId);
		data.put("gender", "Male");
		data.put("status", "Inactive");
		
	int id=	given()
			.header("Authorization","Bearer "+token)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
	
		context.getSuite().setAttribute("User_Id", id);
		context.getSuite().setAttribute("User_Name",fullName);
		context.getSuite().setAttribute("User_MailId", mailId);
		context.getSuite().setAttribute("BearerToken", token);
		
	}

}
