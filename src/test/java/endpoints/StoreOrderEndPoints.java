package endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import reporting.BaseReport;

public class StoreOrderEndPoints {
	
	private static RequestSpecification getRequestSpecification() {
		return RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON);
	}
	
	public static Response createStoreOrder(payload.Store payload) {
		
		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.body(payload).when().post(Routes.order_post);
		BaseReport.printRequestLogInReportWithRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;
		

	/*	Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.when()
				.post(Routes.order_post);
		return response;   */
	}

	public static Response readStoreOrder(int orderID) {
		
		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.pathParam("orderId", orderID).when().get(Routes.order_get);
		BaseReport.printRequestLogInReportWithoutRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;
		
		
		
		
	/*	Response response = given().pathParam("orderId", orderID) // path param must be correct  

				.when().get(Routes.order_get);
		return response; */
	}

	public static Response deleteStoreOrder(int orderID) {
		
		RequestSpecification requestSpecification = getRequestSpecification();
		Response response = requestSpecification.pathParam("orderId", orderID).when().delete(Routes.order_delete);
		BaseReport.printRequestLogInReportWithoutRequestBody(requestSpecification);
		BaseReport.printResponseLogInReport(response);
		return response;
		
		
	/*	Response response = given().pathParam("orderId", orderID)

				.when().delete(Routes.order_delete);
		return response;   */
	}

}
