package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {
	
	// method created for getting url's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load the properties file
		return routes;
	}
	
	public static Response createOrder(Store payload)
	{
		
		String post_url = getURL().getString("order_post_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)	
		.when()
			.post(post_url);
		
		return response;
			
	}
	
	public static Response readOrder(int orderId)
	{
		
		String get_url = getURL().getString("order_get_url");
		Response response = given()
			.pathParam("orderId", orderId)
		.when()
			.get(get_url);
		
		return response;
			
	}
	
	public static Response deleteOrder(int orderId)
	{
		String delete_url = getURL().getString("order_delete_url");
		Response response = given()
			.pathParam("orderId", orderId)
		.when()
			.delete(delete_url);
		
		return response;
			
	}

}
