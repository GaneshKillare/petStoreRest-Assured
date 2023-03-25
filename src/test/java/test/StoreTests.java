package test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.StoreOrderEndPoints;
import io.restassured.response.Response;
import payload.Store;
public class StoreTests {
	Faker faker ;
	payload.Store storepayload;
	public Logger logger ;
	@BeforeClass
	public void setData() {
		faker = new Faker();
		storepayload = new Store();
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
		
		storepayload.setId(faker.idNumber().hashCode());
		storepayload.setPetID(faker.idNumber().hashCode());
		storepayload.setQuantity(faker.number().randomDigit());
		storepayload.setDate(faker.date().birthday().toString());	
		storepayload.setStatus("placed");
		storepayload.setComplete(true);
	}
	@Test
	public void testPostOrder() {
		logger.info("***************** order is creating ********************");
	Response response = StoreOrderEndPoints.createStoreOrder(storepayload);
			//response.then().log().all();
			logger.info("***************** order is created ********************");
	Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 1 , dependsOnMethods = {"testPostOrder"})
	public void testGetOrder() {
		logger.info("***************** Reading order Info ********************");
	Response response =	StoreOrderEndPoints.readStoreOrder(this.storepayload.getId());
	//response.then().log().all();
	logger.info("***************** order Info Displayed  ********************");
	Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority = 2)
	public void testDeleteOrder() {
		logger.info("***************** order is deleting ********************");
	Response response =	StoreOrderEndPoints.deleteStoreOrder(this.storepayload.getId());
	//response.then().log().all();
	Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("***************** order is deleted ********************");
		
	}

}
