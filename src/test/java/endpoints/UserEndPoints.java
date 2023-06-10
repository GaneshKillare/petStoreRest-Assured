package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payload.User;
import reporting.BaseReport;

//UserEndPints.java
// Created for perform Create, Read, Update, Delete requests t the user API.

public class UserEndPoints {

	private static RequestSpecification getRequestSpecification() {
		return RestAssured.given().contentType(ContentType.JSON);
	}

	public static Response createUser(payload.User payload) {
		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.body(payload).post(Routes.post_url);
		BaseReport.printRequestLogInReportWithRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;
	}

	public static Response readUser(String userName) {

		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.pathParam("username", userName).get(Routes.get_url);
		BaseReport.printRequestLogInReportWithoutRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;

	}

	public static Response updateUser(String userName, User payload) {

		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.pathParam("username", userName).body(payload).when()
				.put(Routes.update_url);
		BaseReport.printRequestLogInReportWithRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;

	}

	public static Response deleteUser(String userName) {

		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.pathParam("username", userName).when().delete(Routes.delete_url);
		BaseReport.printRequestLogInReportWithoutRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;

	}

}
