package api.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.payload.PetCategory;
import api.payload.PetTags;
import api.utilities.PetStoreStatus;
import io.restassured.response.Response;

public class PetTests {
	
	Faker faker;
	Pet petPayload;
	PetCategory petCategoryPayload;
	PetTags petTagsPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		petPayload = new Pet();
		petCategoryPayload = new PetCategory();
		petTagsPayload = new PetTags();
		
		petPayload.setId(faker.idNumber().hashCode());
		
		petCategoryPayload.setId(faker.idNumber().hashCode());
		petCategoryPayload.setName(faker.funnyName().name());
		petPayload.setCategory(petCategoryPayload);
		
		petPayload.setName(faker.animal().name());
		
		String[] photourls = {faker.funnyName().name()};
		petPayload.setPhotoUrls(photourls);
		
		petTagsPayload.setId(faker.idNumber().hashCode());
		petTagsPayload.setName(faker.funnyName().name());
		
		List tags = new ArrayList();
		tags.add(petTagsPayload);
		petPayload.setTags(tags);
		
		PetStoreStatus[] status = PetStoreStatus.values(); // Define an array of three words 
		Random random = new Random(); // Create a Random object 
		int randomIndex = random.nextInt(status.length); // Generate a random index to select one of the words 
		petPayload.setStatus(status[randomIndex].toString()); // Select the word based on the random index 
		
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	@Test(priority=1)
	public void testAddNewPet() {
		
		logger.info("************** Adding new pet ********************");
		Response response = PetEndPoints.addNewPet(petPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************** Pet is added ********************");
	}

}
