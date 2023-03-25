package endpoints;

import payload.Store;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreOrderEndPoints {
	public static Response createStoreOrder(payload.Store payload) {

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
				.when()
				.post(Routes.order_post);
		return response;
	}

	public static Response readStoreOrder(int orderID) {
		Response response = given().pathParam("orderId", orderID) // path param must be correct  

				.when().get(Routes.order_get);
		return response;
	}

	public static Response deleteStoreOrder(int orderID) {
		Response response = given().pathParam("orderId", orderID)

				.when().delete(Routes.order_delete);
		return response;
	}

}
