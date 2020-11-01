package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

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
 * @Description : Test cases for Delete Request
 *
 */
public class DeleteRequest extends Log{

	@Test()
	public void verifyDeleteRequest() {
		Log.startTestCase("Verify Delete Request");
		
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponseForDelete(EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		Log.info("Verfy Status Code of API Response Actual : " + response.getStatusCode() + " Expected : "
				+ StaticData.status_200);
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Status Assertion Failed : " + response.getStatusCode());
		Log.info("Response is : " + response.asString());
		Assert.assertEquals(response.asString(), "{}"," Verify response");
		
		Log.startTestCase("Verify Delete Request");

	}

}
