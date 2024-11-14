package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	
	// method created for getting url's from properties file
		static ResourceBundle getURL()
		{
			ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load the properties file
			return routes;
		}
		
		public static Response addNewPet(Pet payload)
		{
			
			String post_url = getURL().getString("pet_post_url");
			Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)	
			.when()
				.post(post_url);
			
			return response;
				
		}

}
