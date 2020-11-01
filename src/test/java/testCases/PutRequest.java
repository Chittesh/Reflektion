package testCases;


import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import Reflektion.org.Reflektion.BasePage;
import Reflektion.org.Reflektion.EnvironmentURLS;
import Reflektion.org.Reflektion.StaticData;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description : Test cases for Put Request
 *
 */
public class PutRequest {

	@Test()
	public void verifyPutRequest() {
		System.out.println("****************************************************************");
		System.out.println("Executing verifyPutRequest Test Case");

		BasePage objBasePage = new BasePage();
		String jsonBody = "{\r\n" + "\"id\": 1,\r\n" + "\"title\": \"abc\",\r\n" + "\"body\": \"xyz\",\r\n"
				+ "\"userId\": 1\r\n" + "}";
		System.out.println("JSON Body is : " + jsonBody);

		Response response = objBasePage.getResponseForPut(jsonBody,
				EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		System.out.println("Status Code of Response is : " + response.getStatusCode());
		System.out.println("Verfying Status Code");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());

		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		System.out.println("Printing out all values from the response schema");
		objBasePage.printOutHashMap(hmObject);

		System.out.println("Verifying the response");
		Assert.assertEquals(hmObject.get("title"), "\"abc\"", "Verify Title");
		Assert.assertEquals(hmObject.get("body"), "\"xyz\"", "Verify Body");
		Assert.assertEquals(hmObject.get("userId"), "1", "Verify userId");
		Assert.assertEquals(hmObject.get("id"), "1", "Verify id");

		System.out.println("Completed Executing verifyPutRequest Test Case");
		System.out.println("****************************************************************");

	}

}
