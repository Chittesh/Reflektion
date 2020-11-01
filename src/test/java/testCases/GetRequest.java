package testCases;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import Reflektion.org.Reflektion.BasePage;
import Reflektion.org.Reflektion.EnvironmentURLS;
import Reflektion.org.Reflektion.StaticData;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description : Test cases for Get Request
 *
 */
public class GetRequest {

	@Test()
	public void verifyAtlestHundredRecordsAreBeingReturned() {
		System.out.println("****************************************************************");
		System.out.println("Executing verifyAtlestHundredRecordsAreBeingReturned Test Case");
		
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponse(EnvironmentURLS.getpostsUrl());
		System.out.println("Status Code of Response is : " + response.getStatusCode());
		System.out.println("Verfying Status Code");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());
		int sizeOfJsonObejcts = 0;
		sizeOfJsonObejcts = objJsonElement.getAsJsonArray().size();
		System.out.println("No Of Records being fetched from response is : " + sizeOfJsonObejcts);
		System.out.println("Verifying No Of Records");
		Assert.assertTrue(sizeOfJsonObejcts >= 100, "Verify atleast 100 records are fetched");
		
		System.out.println("Completed Executing verifyAtlestHundredRecordsAreBeingReturned Test Case");
		System.out.println("****************************************************************");

	}

	@Test()
	public void verifyOnlyOneRecordIsBeingReturned() {
		System.out.println("****************************************************************");
		System.out.println("Executing verifyOnlyOneRecordIsBeingReturned Test Case");
		
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponse(EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		System.out.println("Status Code of Response is : " + response.getStatusCode());
		System.out.println("Verfying Status Code");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());

		JsonArray objJsonArray = new JsonArray();
		objJsonArray.add(objJsonElement);
		System.out.println("No Of Records being fetched from response is : " + objJsonArray.size());
		System.out.println("Verifying No Of Records");
		Assert.assertTrue(objJsonArray.size() == 1, "Verify atleast 1 record are fetched");

		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		System.out.println("Printing out all values from the response schema");
		objBasePage.printOutHashMap(hmObject);

		System.out.println("Verifying id from response with Expected value as 1");
		Assert.assertEquals(hmObject.get("id"), "1", "Verify ID value from response");
		
		System.out.println("Completed Executing verifyOnlyOneRecordIsBeingReturned Test Case");
		System.out.println("****************************************************************");

	}

	@Test()
	public void verifyInvalidGetRequest() {
		System.out.println("****************************************************************");
		System.out.println("Executing verifyInvalidGetRequest Test Case");
		
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponseWithLogs(EnvironmentURLS.getInvalidUrl());
		System.out.println("Status Code of Response is : " + response.getStatusCode());
		System.out.println("Verfying Status Code");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_404,
				"Verify Status : " + response.getStatusCode());
		
		System.out.println("Completed Executing verifyInvalidGetRequest Test Case");
		System.out.println("****************************************************************");

	}

}
