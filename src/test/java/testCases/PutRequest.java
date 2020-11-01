package testCases;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import Reflektion.org.Reflektion.BasePage;
import Reflektion.org.Reflektion.EnvironmentURLS;
import Reflektion.org.Reflektion.Log;
import Reflektion.org.Reflektion.StaticData;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description : Test cases for Put Request
 *
 */
public class PutRequest extends Log {

	@Test()
	public void verifyPutRequest() {
		Log.startTestCase("Verify Put Request");

		BasePage objBasePage = new BasePage();
		String jsonBody = "{\r\n" + "\"id\": 1,\r\n" + "\"title\": \"abc\",\r\n" + "\"body\": \"xyz\",\r\n"
				+ "\"userId\": 1\r\n" + "}";
		Log.info("JSON Body is : " + jsonBody);
		Response response = objBasePage.getResponseForPut(jsonBody,
				EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		Log.info("Verfy Status Code of API Response Actual : " + response.getStatusCode() + " Expected : "
				+ StaticData.status_200);
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());
		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());
		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		Log.info("Printing out all values from the response schema");
		objBasePage.printOutHashMap(hmObject);
		Log.info("Verifying the response");
		Log.info("Verifying response title Acutal : " + hmObject.get("title") + "Expected is : \"abc\"");
		Assert.assertEquals(hmObject.get("title"), "\"abc\"", "Verify Title");
		Log.info("Verifying response body Acutal : " + hmObject.get("body") + "Expected is : \"xyz\"");
		Assert.assertEquals(hmObject.get("body"), "\"xyz\"", "Verify Body");
		Log.info("Verifying response userId Acutal : " + hmObject.get("userId") + "Expected is : 1");
		Assert.assertEquals(hmObject.get("userId"), "1", "Verify userId");
		Log.info("Verifying response userId Acutal : " + hmObject.get("id") + "Expected is : 1");
		Assert.assertEquals(hmObject.get("id"), "1", "Verify id");

		Log.endTestCase("Verify Put Request");
	}

}
