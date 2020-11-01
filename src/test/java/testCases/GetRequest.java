package testCases;

import java.io.File;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import Reflektion.org.Reflektion.BasePage;
import Reflektion.org.Reflektion.EnvironmentURLS;
import Reflektion.org.Reflektion.Log;
import Reflektion.org.Reflektion.StaticData;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description : Test cases for Get Request
 *
 */
public class GetRequest extends Log {

	@Test()
	public void verifyAtlestHundredRecordsAreBeingReturned() {
		Log.startTestCase("Verify At least Hundred Records Are Being Returned");

		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponse(EnvironmentURLS.getpostsUrl());
		Log.info("Verfy Status Code of API Response Actual : " + response.getStatusCode() + " Expected : "
				+ StaticData.status_200);
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Status Assertion Failed : " + response.getStatusCode());
		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());
		int sizeOfJsonObejcts = 0;
		sizeOfJsonObejcts = objJsonElement.getAsJsonArray().size();
		Log.info("No Of Records being fetched from response is : " + sizeOfJsonObejcts);
		Log.info("Verify atleast 100 Records are fetched");
		Assert.assertTrue(sizeOfJsonObejcts >= 100, "Failed : Less than 100 Records");

		Log.endTestCase("Verify At least Hundred Records Are Being Returned");

	}

	@Test()
	public void verifyOnlyOneRecordIsBeingReturned() {
		Log.startTestCase("Verify Only One Record Is Being Returned");

		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponse(EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		Log.info("Verfy Status Code of API Response Actual : " + response.getStatusCode() + " Expected : "
				+ StaticData.status_200);
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());
		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());
		JsonArray objJsonArray = new JsonArray();
		objJsonArray.add(objJsonElement);
		Log.info("No Of Records being fetched from response is : " + objJsonArray.size());
		Log.info("Verify No Of Records returned is 1");
		Assert.assertTrue(objJsonArray.size() == 1, "No Of records fetched is not 1");
		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		Log.info("Printing out all values from the response schema");
		objBasePage.printOutHashMap(hmObject);
		Log.info("Verifying id from API Response Actual : " + hmObject.get("id") + " Expected : " + 1);
		Assert.assertEquals(hmObject.get("id"), "1", "Verify ID value from response");

		Log.endTestCase("Verify Only One Record Is Being Returned");

	}

	@Test()
	public void verifyInvalidGetRequest() {
		Log.startTestCase("verify Invalid Get Request");

		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponseWithLogs(EnvironmentURLS.getInvalidUrl());
		Log.info("Verfy Status Code of API Response Actual : " + response.getStatusCode() + " Expected : "
				+ StaticData.status_404);
		Log.info("Check Error on Console Log");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_404,
				"Verify Status : " + response.getStatusCode());
		
		Log.endTestCase("verify Invalid Get Request");
	}

}
