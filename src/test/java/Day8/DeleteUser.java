package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class DeleteUser {
	
	@Test
	void Test_Deleteuser(ITestContext context)
	{
		String token=(String) context.getSuite().getAttribute("BearerToken");
		int id=(Integer)context.getSuite().getAttribute("User_Id");
		
		given()
			.header("Authorization","Bearer "+token)
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204);
	}

}
