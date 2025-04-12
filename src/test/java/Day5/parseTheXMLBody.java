package Day5;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class parseTheXMLBody {
	
	
	//Approach 1
	/*@Test
	void ValideteXMLBody()
	{
		given()
			
		.when()
			.get("http://localhost:5000/api/students")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml")
			.body("StudentsResponse.PageNumber", equalTo("1"))
			.body("StudentsResponse.Students.Student[2].FirstName", equalTo("Michael"));
			
	}*/
	
	//Approach 2
/*	@Test
	void ValidateXMLBody()
	{
		Response res=
		given()
		
		.when()
			.get("http://localhost:5000/api/students");
		
		String stuName=res.xmlPath().get("StudentsResponse.Students.Student[2].FirstName").toString();
		Assert.assertEquals(stuName, "Michael");
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml");			
	}*/
	
	//Approach 3
	@Test
	void validateXMlBody()
	{
		Response res=
		given()
		
		.when()
			.get("http://localhost:5000/api/students");
		
		XmlPath xo=new XmlPath(res.asString());
		boolean falg=false;
		List<String>fNames=xo.getList("StudentsResponse.Students.Student.FirstName");
		for(String firstName:fNames)
		{
			if(firstName.equals("Michael"))
			{
				falg=true;
				break;
			}
		}
		Assert.assertTrue("Did Not Match", falg);
		
		

            
	}

}
