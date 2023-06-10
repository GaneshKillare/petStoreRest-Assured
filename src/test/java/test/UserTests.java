package test;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payload.User;
import utilities.XLUtility;

public class UserTests {
	Faker faker;
	payload.User userpayload;
	public Logger logger; // for log
	ObjectMapper objMapper;
	public String log;
	XLUtility xLUtility;

	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userpayload = new User();
		objMapper = new ObjectMapper();

		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}

	@Test(priority = 0)
	public void testPostUser() throws IOException {
		logger.info("*********** Creating User ************");
		Response response = UserEndPoints.createUser(userpayload);

		// String requestBody = baseClass.postRequestDataSetterInJson(userpayload,
		// response);
		Assert.assertEquals(response.getStatusCode(), 200);

		logger.info("************* User is Created ***************");

	}

	@Test(priority = 2)
	public void testGetUserByName() throws JsonProcessingException {
		logger.info("********** Reading User Info ***************");
		Response response = UserEndPoints.readUser(this.userpayload.getUsername());

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User info  is displayed ***************");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		// Update data using payload

		logger.info("********** Updating user ***********");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User Updated ***********");
		/*
		 * // checking data after update Response responseAfterUpdate =
		 * UserEndPoints.readUser(this.userpayload.getUsername());
		 * Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		 */

	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("*************** Deleting User **************");
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********** User Deleted ***************");
	}

}
