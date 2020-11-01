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
	
	public BasePage() {
		System.out.println("Base URL is : " + EnvironmentURLS.getBaseUrl());
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
	}

	/**
	 * @Description :
	 * @param :
	 * @return 
	 * @return :
	 * @Date :
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
	
	public void printOutHashMap(HashMap<String, String> hmObject) {
		hmObject.entrySet().forEach(entry -> {
			System.out.println("Object Key id : '"+ entry.getKey() + "' - Object value is : '" + entry.getValue()+"'");
		});
	}
	
	
	 
	
	
	

}
