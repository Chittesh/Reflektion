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
public class GetRequest {

	@Test()
	public void verifyAtlestHundredRecordsAreBeingReturned() {
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponse(EnvironmentURLS.getpostsUrl());
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());
		int sizeOfJsonObejcts = 0;
		sizeOfJsonObejcts = objJsonElement.getAsJsonArray().size();

		Assert.assertTrue(sizeOfJsonObejcts >= 100, "Verify atleast 100 records are fetched");

	}

	@Test()
	public void verifyOnlyOneRecordIsBeingReturned() {
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponse(EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());

		JsonArray objJsonArray = new JsonArray();
		objJsonArray.add(objJsonElement);
		Assert.assertTrue(objJsonArray.size() == 1, "Verify atleast 1 record are fetched");

		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = objBasePage.getKeysMehthod(objJsonElement);
		objBasePage.printOutHashMap(hmObject);

		Assert.assertEquals(hmObject.get("id"), "1", "Verify ID value from response");

	}

	@Test()
	public void verifyInvalidGetRequest() {
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponseWithLogs(EnvironmentURLS.getInvalidUrl());
		Assert.assertEquals(response.getStatusCode(), StaticData.status_404,
				"Verify Status : " + response.getStatusCode());

	}

}
