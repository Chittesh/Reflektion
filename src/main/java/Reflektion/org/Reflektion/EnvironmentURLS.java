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
	 * @Description : 
	 * @param : 
	 * @return : 
	 * @Date : 
	 */
	public static String getBaseUrl() {
		return userCreditinals.getString("baseUrl");
	}

	/**
	 * @Description : 
	 * @param : 
	 * @return : 
	 * @Date : 
	 */
	public static String getpostsUrl() {
		return userCreditinals.getString("posts");
	}
	
	/**
	 * @Description : 
	 * @param : 
	 * @return : 
	 * @Date : 
	 */
	public static String getInputUrl() {
		return userCreditinals.getString("input_1");
	}
	
	/**
	 * @Description : 
	 * @param : 
	 * @return : 
	 * @Date : 
	 */
	public static String getInvalidUrl() {
		return userCreditinals.getString("invalid");
	}


}
