package Reflektion.org.Reflektion;

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
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * @author chicharles
 * @Description :
 *
 */
public class BasePage {

	/**
	 * @Description :
	 * @param :
	 * @return :
	 * @Date :
	 */
	@Test()
	public void verifyAtlestHundredRecordsAreBeingReturned() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson).when()
				.get(EnvironmentURLS.getpostsUrl()).then().extract().response();
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
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson).when()
				.get(EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl()).then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());

		JsonParser p = new JsonParser();
		JsonElement objJsonElement = p.parse(response.asString());

		int sizeOfJsonObejcts = 0;
		JsonArray objJsonArray = new JsonArray();
		objJsonArray.add(objJsonElement);

		Assert.assertTrue(objJsonArray.size() == 1, "Verify atleast 1 record are fetched");

		HashMap<String, String> hmObject = new HashMap<String, String>();
		hmObject = getKeysMehthod(objJsonElement);

		Assert.assertEquals(hmObject.get("id"), "1", "Verify ID value from response");

	}

	@Test()
	public void verifyInvalidGetRequest() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson).log().all().when()
				.get(EnvironmentURLS.getInvalidUrl()).then().log().all().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_404,
				"Verify Status : " + response.getStatusCode());

	}

	public static HashMap<String, String> getKeysMehthod(JsonElement objJsonElement) {
		Set<Map.Entry<String, JsonElement>> entrySet = objJsonElement.getAsJsonObject().entrySet();
		HashMap<String, String> hm = new HashMap<String, String>();
		for (Map.Entry<String, JsonElement> entry : entrySet) {
			if (entry.getValue().isJsonObject()) {
				getKeysMehthod(entry.getValue());
			} else if (entry.getValue().isJsonArray()) {
				hm.put(entry.getKey(), entry.getValue().toString());
			} else {
				hm.put(entry.getKey(), entry.getValue().toString());
			}
		}
		return hm;
	}

}
