package Day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Day1 {
	int id;
	@Test(priority = 1)
	public void createAUser()
	{
		HashMap datas=new HashMap();
		datas.put("name", "Anisha");
		datas.put("job", "qa-lead");
		
		id=given()
			.contentType("application/json")
			.body(datas)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
	}
	@Test(priority = 2)
	public void updateUser()
	{
		HashMap datas=new HashMap();
		datas.put("name", "AnishaMohanty");
		datas.put("job", "qa-lead");
		
		given()
			.contentType("application/json")
			.body(datas)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200);
	}
	@Test(priority = 3)
	public void deleteTheuser()
	{
		when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204);
			
	}
	
	@Test(priority = 4)
	public void getUsers()
	{
		when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2));
	}
	
	

}
