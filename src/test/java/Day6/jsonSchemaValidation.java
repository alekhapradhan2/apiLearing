package Day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


import io.restassured.module.jsv.JsonSchemaValidator;

public class jsonSchemaValidation {
	
	@Test
	void schemaValidation()

	{
		given()
		
		.when()
			.get("http://localhost:3000/students")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("josnSchema.json"));
		
		
	}

}
