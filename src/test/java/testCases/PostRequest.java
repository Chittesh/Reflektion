package testCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Reflektion.org.Reflektion.BasePage;
import Reflektion.org.Reflektion.EnvironmentURLS;
import Reflektion.org.Reflektion.StaticData;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description :
 *
 */
public class PostRequest {

	@Test()
	public void verifyAtlestHundredRecordsAreBeingReturned() {
		BasePage objBasePage = new BasePage();
		String jsonBody = "{\r\n" + "\"title\": \"foo\",\r\n" + "\"body\": \"bar\",\r\n" + "\"userId\": 1\r\n" + "}";

		Response response = objBasePage.getResponseForPost(jsonBody, EnvironmentURLS.getpostsUrl());
		Assert.assertEquals(response.getStatusCode(), StaticData.status_201,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());

		JsonArray objJsonArray = new JsonArray();
		objJsonArray.add(objJsonElement);
		Assert.assertTrue(objJsonArray.size() == 1, "Verify atleast 1 record are fetched");

		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		objBasePage.printOutHashMap(hmObject);

	}

}
