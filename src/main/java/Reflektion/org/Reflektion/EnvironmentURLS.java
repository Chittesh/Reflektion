package Reflektion.org.Reflektion;

import java.util.ResourceBundle;

/**
 * @author chicharles
 * @Description : This class contains common method to fetch the API urls
 *
 */
public class EnvironmentURLS {
	static ResourceBundle userCreditinals = ResourceBundle.getBundle("urls");

	/**
	 * @Description : To get the BaseUrl
	 * @param : NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getBaseUrl() {
		return userCreditinals.getString("baseUrl");
	}

	/**
	 * @Description : To get the Post url
	 * @param :NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getpostsUrl() {
		return userCreditinals.getString("posts");
	}

	/**
	 * @Description : To get the Input url
	 * @param : NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getInputUrl() {
		return userCreditinals.getString("input_1");
	}

	/**
	 * @Description : To get the invalid url
	 * @param :  NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getInvalidUrl() {
		return userCreditinals.getString("invalid");
	}

}
