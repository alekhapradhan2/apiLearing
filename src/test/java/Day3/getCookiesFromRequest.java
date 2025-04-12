package Day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class getCookiesFromRequest {
	
	@Test(priority = 1)
	void getSingleCookies()
	{
		Response res;
		res=given()
		.when()
			.get("https://google.com");
		
		String cokk=res.getCookie("AEC");
		System.out.println("ACE===>"+cokk);
		
		when()
			.get("https://google.com")
		.then()
			.log().cookies();
		
	}
	
	@Test(priority = 2)
	public void getAllCookies()
	{
		Response res;
		res=given()
		.when()
			.get("https://google.com");
		
		Map<String, String> allCookies=res.getCookies();
		System.out.println(allCookies.size());
		
		for(String k:allCookies.keySet())
		{
			String cookie=res.getCookie(k);
			System.out.println(k+"====>"+cookie);
		}
	}
	
	

}
