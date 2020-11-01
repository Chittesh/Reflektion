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
 * @Description : This Page contains reusable methods
 *
 */
public class BasePage{
	
	public BasePage() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
	}

	/**
	 * @Description : Method that will fetch the response from get request
	 * @param : urls
	 * @return :Response
	 * @Date : 11/1/2020
	 */
	public Response getResponse(String urls) {
		Response response = given()
							.header(StaticData.contentHeader, StaticData.contentTypeJson)
							.when()
							.get(urls)
							.then()
							.extract()
							.response();
		return response;
	}


	/**
	 * @Description : Method that will fetch the response with logs from get request
	 * @param : urls
	 * @return :Response
	 * @Date : 11/1/2020
	 */
	public Response getResponseWithLogs(String urls) {
		Response response = given()
							.header(StaticData.contentHeader, StaticData.contentTypeJson)
							.log().all()
							.when()
							.get(urls)
							.then()
							.log().all()
							.extract()
							.response();
		return response;

	}
	
	/**
	 * @Description : Method that will fetch the response from Delete request
	 * @param : urls
	 * @return :Response
	 * @Date : 11/1/2020
	 */
	public Response getResponseForDelete(String urls) {
		Response response = given()
							.header(StaticData.contentHeader, StaticData.contentTypeJson)
							.log().all()
							.when()
							.delete(urls)
							.then()
							.extract()
							.response();
		return response;

	}

	/**
	 * @Description : Method that will fetch the response from Post request
	 * @param :  jsonBody,  postingUrl
	 * @return :Response
	 * @Date : 11/1/2020
	 */
	public Response getResponseForPost(String jsonBody, String postingUrl) {
		Response response = given()
							.header(StaticData.contentHeader, StaticData.contentTypeJson)
							.body(jsonBody)
							.when()
							.post(postingUrl)
							.then()
							.extract()
							.response();
		return response;
	}
	
	/**
	 * @Description : Method that will fetch the response from Put request
	 * @param :  jsonBody,  postingUrl
	 * @return :Response
	 * @Date : 11/1/2020
	 */
	public Response getResponseForPut(String jsonBody, String postingUrl) {
		Response response = given()
							.header(StaticData.contentHeader, StaticData.contentTypeJson)
							.body(jsonBody)
							.when()
							.put(postingUrl)
							.then()
							.extract()
							.response();
		return response;
	}

	/**
	 * @Description : Method that return a collection from the json schema data
	 * @param :  JsonElement
	 * @return : HashMap<String, String> getKeysMehthod
	 * @Date : 11/1/2020
	 */
	public static HashMap<String, String> getKeysMehthod(JsonElement objJsonElement) {
		// Getting the entrySet from JsonElement
		Set<Map.Entry<String, JsonElement>> entrySet = objJsonElement.getAsJsonObject().entrySet();
		// Declaring the HashMap
		HashMap<String, String> hm = new HashMap<String, String>();

		for (Map.Entry<String, JsonElement> entry : entrySet) {
			// Checking if values is JsonObject
			if (entry.getValue().isJsonObject()) {
				// Recursive Function call
				getKeysMehthod(entry.getValue());
				// Checking if values is JsonArray
			} else if (entry.getValue().isJsonArray()) {
				hm.put(entry.getKey(), entry.getValue().toString());
			} else {
				// Adding values to hashmap
				hm.put(entry.getKey(), entry.getValue().toString());
			}
		}
		return hm;
	}
	
	/**
	 * @Description : Method to print out values from HashMap
	 * @param :  HashMap<String, String>
	 * @return : void
	 * @Date : 11/1/2020
	 */
	public void printOutHashMap(HashMap<String, String> hmObject) {
		Log.info("****************************************************************************");
		hmObject.entrySet().forEach(entry -> {
			Log.info("Object Key id : '"+ entry.getKey() + "' - Object value is : '" + entry.getValue()+"'");
		});
		Log.info("****************************************************************************");
	}
	
	
	 
	
	
	

}
