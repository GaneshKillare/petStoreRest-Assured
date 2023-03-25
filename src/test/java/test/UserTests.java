package test;

import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.impl.ExtendedClassInfo;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.User;
import utilities.ExtentReportManager;

public class UserTests {
	ExtentReportManager extent ;
	Faker faker;
	payload.User userpayload;
	public Logger logger; // for log

	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userpayload = new User();
		extent = new ExtentReportManager();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	

	@Test
	public void testPostUser() throws JsonProcessingException {
		logger.info("*********** Creating User ************");
		Response response = UserEndPoints.createUser(userpayload);
		//response.then().log().all();
		 String log = response.then().log().all().toString();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* User is Created ***************");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("********** Reading User Info ***************");
		Response response = UserEndPoints.readUser(this.userpayload.getUsername());
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User info  is displayed ***************");
		String log = response.then().log().all().toString();
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		// Update data using payload

		logger.info("********** Updating user ***********");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User Updated ***********");
		String log = response.then().log().all().toString();
		// checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("*************** Deleting User **************");
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********** User Deleted ***************");
	}

	
}
