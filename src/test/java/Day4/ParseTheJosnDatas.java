package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseTheJosnDatas {
	
	//Approach 1
/*	@Test
	void validateDatasfromJson()
	{
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.body("book[3].title",equalTo("Empty Book"));
	}*/
	
	//Approach 2
	
	/*@Test
	void validateDatasFromJson()
	{
		Response res=
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		String name=res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(name, "Empty Book");
	}
	*/
	
	//Appraoch 3
	
	@Test
	void getAllDatasFromJSON()
	{
/*		
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		JSONObject jo=new JSONObject(res.asString());
		JSONArray arr=jo.getJSONArray("book");
//		for(int i=0;i<jo.getJSONArray("book").length();i++)
//		{
//			String title=jo.getJSONArray("book").getJSONObject(i).getString("title");
//			System.out.println(title);
//			
//		}
		
		
//		boolean flag=false;
//		for(int i=0;i<jo.getJSONArray("book").length();i++)
//		{
//			String title=jo.getJSONArray("book").getJSONObject(i).getString("author");
//			if(title.equals("Alekha1"))
//			{
//				flag=true;
//			}
//		}
//		Assert.assertTrue(flag,"Data not matched");
		
		double price=0;
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String priceValue=jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			price=price+Double.parseDouble(priceValue);
	
		}
		
		System.out.println(price);*/
		
		Response res=
			given()
				.contentType(ContentType.JSON)
			.when()
				.get("https://reqres.in/api/users?page=2");
			
		JSONObject jo=new JSONObject(res.asString());
		
		JSONArray arr=jo.getJSONArray("data");
		
		for(int i=0;i<arr.length();i++)
		{
			String email=arr.getJSONObject(i).get("email").toString();
			System.out.println(email);
		}
		
		System.out.println(res.jsonPath().get("total").toString());
		
				
		
		
	}
	
	

}
