package reporting;

import java.util.Arrays;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.http.Header;

public class Setup implements ITestListener {

	public static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public void onStart(ITestContext context) {
		String fileName = ExtentReportManager.getReportWIthTimestamp();
		String fullReportPath = System.getProperty("user.dir") + "\\reports\\" + fileName;
		extentReports = ExtentReportManager.createInstance(fullReportPath, "Test API Automation Report",
				"Test ExecutionReport");
	}

	public void onFinish(ITestContext context) {
		if (extentReports != null)
			extentReports.flush();
	}

	public void onTestStart(ITestResult result) {
		ExtentTest test = extentReports.createTest(
				"Test Name " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		extentTest.set(test);
	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		stackTrace = stackTrace.replaceAll(",", "<br>");
		String formmatedTrace = "<details>\n" + "    <summary>Click Here To See Exception Logs</summary>\n" + "    "
				+ stackTrace + "\n" + "</details>\n";
		ExtentReportManager.logExceptionDetails(formmatedTrace);
	}

}
