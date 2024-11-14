package api.test;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.utilities.PetStoreStatus;
import api.utilities.RandomDateAndTime;
import io.restassured.response.Response;

public class StoreTests {
	
	Faker faker;
	Store storePayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		storePayload = new Store();
		
		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setPetId(faker.idNumber().hashCode());
		storePayload.setQuantity(faker.number().numberBetween(10, 99));
		storePayload.setShipDate(RandomDateAndTime.createISO8601Time());
		
		
		PetStoreStatus[] status = PetStoreStatus.values(); // Define an array of three words 
		Random random = new Random(); // Create a Random object 
		int randomIndex = random.nextInt(status.length); // Generate a random index to select one of the words 
		storePayload.setStatus(status[randomIndex].toString()); // Select the word based on the random index 
		
		storePayload.setComplete(faker.bool().bool());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	@Test(priority=1)
	public void testPostOrder() {
		
		logger.info("************** Creating order ********************");
		Response response = StoreEndPoints.createOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************** Order is created ********************");
	}
	
	@Test(priority=2)
	public void testGetOrderById() {
		
		logger.info("************** Reading order Info ********************");
		Response response = StoreEndPoints.readOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** Order Info is displayed********************");
	}
	
	
	@Test(priority=3)
	public void testDeleteOrderById() {
		
		logger.info("************** Deleting order   ********************");
		Response response = StoreEndPoints.deleteOrder(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************** Order deleted ********************");
	}

}
