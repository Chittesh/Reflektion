package Reflektion.org.Reflektion;

import java.util.ResourceBundle;

/**
 * @author chicharles
 * @Description : This class contains common method to fetch the API urls
 *
 */
public class EnvironmentURLS extends Log{
	static ResourceBundle userCreditinals = ResourceBundle.getBundle("urls");

	/**
	 * @Description : To get the BaseUrl
	 * @param : NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getBaseUrl() {
		Log.info("BASE URL : "+userCreditinals.getString("baseUrl"));
		return userCreditinals.getString("baseUrl");
	}

	/**
	 * @Description : To get the Post url
	 * @param :NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getpostsUrl() {
		Log.info("Append POST URL : "+userCreditinals.getString("posts"));
		return userCreditinals.getString("posts");
	}

	/**
	 * @Description : To get the Input url
	 * @param : NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getInputUrl() {
		Log.info("Append Input URL : "+userCreditinals.getString("input_1"));
		return userCreditinals.getString("input_1");
	}

	/**
	 * @Description : To get the invalid url
	 * @param :  NA
	 * @return : String
	 * @Date : 11/1/2020
	 */
	public static String getInvalidUrl() {
		Log.info("Append Invalid URL : "+userCreditinals.getString("invalid"));
		return userCreditinals.getString("invalid");
	}

}
