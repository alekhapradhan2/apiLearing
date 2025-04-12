package Day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class getHeadersFromRequest {
	@Test(priority = 1)
	void getSingleHeader()
	{
		Response res;
		res=given()
		
		.when()
			.get("https://google.com");
		
		String h=res.getHeader("Content-Type");
		System.out.println(h);

	}
	@Test(priority = 2)
	void getMultipleHeaders()
	{
		Response res;
		res=given()
		
		.when()
			.get("https://google.com");
		
		Headers hs=res.getHeaders();
		for(Header k:hs)
		{
			System.out.println(k.getName()+"===>"+k.getValue());
			
		}
		
		
	}

}
