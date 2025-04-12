package Day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;



public class xmlSchemValidation {
	
	
	@Test
	void xmlSchema()
	{
		given()
		
		.when()
			.get("http://localhost:5000/api/students")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xml"));
	}
	
	

}
