package reporting;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class BaseReport {
	
	
	public static void printRequestLogInReportWithRequestBody(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		ExtentReportManager.logInfoDetails("Endpoint is " + queryableRequestSpecification.getURI());
		ExtentReportManager.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
		ExtentReportManager.logInfoDetails("Headers are ");
 		ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
		ExtentReportManager.logInfoDetails("Request body is ");
		ExtentReportManager.logJSONDetails(queryableRequestSpecification.getBody().toString());
	}
	public static void printRequestLogInReportWithoutRequestBody(RequestSpecification requestSpecification) {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
		ExtentReportManager.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBaseUri());
		ExtentReportManager.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
		ExtentReportManager.logInfoDetails("Headers are ");
		ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
	}

	public static void printResponseLogInReport(Response response) {
		ExtentReportManager.logInfoDetails("Response status is " + response.getStatusCode());
		ExtentReportManager.logInfoDetails("Response Headers are ");
		ExtentReportManager.logHeaders(response.getHeaders().asList());
		ExtentReportManager.logInfoDetails("Response body is ");
		ExtentReportManager.logJSONDetails(response.getBody().prettyPrint());
	} 
	// Write request in excel
		  /*  objMapper = new ObjectMapper();
			String requestPayload1 = objMapper.writeValueAsString(this.userpayload.getUsername());
			JsonNode jsonNode = objMapper.readTree(requestPayload1);
			String requestPayload = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
			String responsePayload = response.asPrettyString();  */
	
	
	
	
			/*xLUtility = new XLUtility("test1.xlsx");
			xLUtility.writeDataCell("API Request", dataList); */

}
