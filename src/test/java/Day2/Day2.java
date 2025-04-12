package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class Day2 {
	
	//using HashMap
	
/*	@Test(priority = 1)
	void creatUserUsingHasMap()
	{
		HashMap datas=new HashMap();
		datas.put("id", "4");
		datas.put("name", "Nikhil");
		datas.put("Class", "MCA");
		datas.put("address", "Berhampur");
		String coursesArr[]= {"JavaScript","Python"};
		datas.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(datas)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)


	}
	
	@Test(priority = 2)
	void deleteUser()
	{
		when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200);
	}
	*/
	
	//Using Org.Josn
/*	@Test(priority = 1)
	void createUser()
	{
		JSONObject datas=new JSONObject();
		datas.put("id", "78");
		datas.put("name", "Nikhil");
		datas.put("Class", "MCA");
		datas.put("address", "Berhampur");
		String coursesArr[]= {"JavaScript","Python"};
		datas.put("courses", coursesArr);
		
		given()
			.contentType("application/json")
			.body(datas.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("id", equalTo("78"))
			.body("name",equalTo("Nikhil"))
			.body("Class", equalTo("MCA"))
			.body("address", equalTo("Berhampur"))
			.body("courses[0]", equalTo("JavaScript"))
			.body("courses[1]", equalTo("Python"));

	}
	
	@Test(priority = 2)
	void deleteUser()
	{
		when()
			.delete("http://localhost:3000/students/78")
		.then()
			.statusCode(200);
	}
	
	*/
	
	//Using Pojo Class(Plain old java object)
/*	@Test(priority = 1)
	void createuser()
	{
		POJO datas=new POJO();
		datas.setId("85");
		datas.setName("Anisha");
		datas.setDegree("Journalism");
		datas.setAddress("Jajpur");
		String courseArr[]= {"Manual Testing","Automation Testing"};
		datas.setCourses(courseArr);
		
		given()
			.contentType("application/json")
			.body(datas)
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("id", equalTo("85"))
		.body("name",equalTo("Anisha"))
		.body("degree", equalTo("Journalism"))
		.body("address", equalTo("Jajpur"))
		.body("courses[0]", equalTo("Manual Testing"))
		.body("courses[1]", equalTo("Automation Testing"));
			
	}
	
	@Test(priority = 2)
	void deleteUser()
	{
		when()
			.delete("http://localhost:3000/students/85")
		.then()
			.statusCode(200);
	}
	*/
	
//	Using external json file
/*	@Test
	void createuser() throws FileNotFoundException
	{
		File f=new File("src/test/resources/students.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject datas=new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(datas.toString())
			.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201);
	}
	*/
	
	
}
