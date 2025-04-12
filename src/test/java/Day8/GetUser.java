package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class GetUser {
	
	
	@Test
	void test_getuser(ITestContext context)
	{
		String token=(String) context.getSuite().getAttribute("BearerToken");
		int id=(Integer)context.getSuite().getAttribute("User_Id");
		String fullName=(String) context.getSuite().getAttribute("User_Name");
		String mailId=(String) context.getSuite().getAttribute("User_MailId");
		
		given()
			.header("Authorization","Bearer "+token)
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all()
			.body("name",equalTo(fullName))
			.body("email", equalTo(mailId));
			
		
	}

}
