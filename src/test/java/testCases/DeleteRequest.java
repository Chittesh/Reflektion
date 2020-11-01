package testCases;


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
 * @Description : Test cases for Delete Request
 *
 */
public class DeleteRequest {

	@Test()
	public void verifyDeleteRequest() {
		System.out.println("****************************************************************");
		System.out.println("Executing verifyDeleteRequest Test Case");
		
		BasePage objBasePage = new BasePage();
		Response response = objBasePage.getResponseForDelete(EnvironmentURLS.getpostsUrl() + EnvironmentURLS.getInputUrl());
		System.out.println("Status Code of Response is : " + response.getStatusCode());
		System.out.println("Verfying Status Code");
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());
		
		
		System.out.println("Response is : " + response.asString());
		Assert.assertEquals(response.asString(), "{}"," Verify response");
		
		
		System.out.println("Completed Executing verifyDeleteRequest Test Case");
		System.out.println("****************************************************************");

	}

}
