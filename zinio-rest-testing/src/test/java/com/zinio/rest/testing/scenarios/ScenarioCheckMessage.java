package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.actions.Authenticate;
import com.zinio.rest.testing.actions.Order;
import com.zinio.rest.testing.actions.User;
/**
 * Scenario to check message responses 
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioCheckMessage extends Scenario{
	
	/**
	 * Check message in regular order preparation 
	 * @param json
	 * @param statusCode
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "json", "statusCode", "username", "password"})
	public void checkMessage(String json, String statusCode, String username, String password)
	{
		try	{
			Reporter.log("Check message test steps:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			auth.loginByPassword(statusCode, username, password);
			Order order = new Order(configuration,jsonDirectory+json);
			order.ordersPrepare(auth, statusCode);
		}
		catch(Exception e){
			Reporter.log("Check message failed: " + e.getMessage());
		}
	}
	/**
	 * Check message when token is invalid
	 * @param json
	 * @param statusCode
	 * @param errorCode
	 */
	@Test
	@Parameters({ "json", "statusCode","errorCode" })
	public void checkMessageInvalidToken(String json, String statusCode, String errorCode){
		try{
			Reporter.log("Check message test steps:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			Order order = new Order(configuration, jsonDirectory+json);
			Response orderResponse = order.userSelfOrders(auth, statusCode);
			hardAssert.assertEquals(orderResponse.jsonPath().getString("errorCode"), errorCode);
		}
		catch(Exception e){
			Reporter.log("Check message failed: " + e.getMessage());
		}
	}
	/**
	 * Check message when try to purchase same SKU id 
	 * @param json
	 * @param authStatusCode
	 * @param orderStatusCode
	 * @param errorCode
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "json", "authStatusCode","orderStatusCode","errorCode", "username","password"})
	public void checkMessageExistingSkuid(String json, String authStatusCode, String orderStatusCode, String errorCode,  String username, String password){
		try{
			Reporter.log("Check message with existing skuid test steps:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			auth.loginByPassword(authStatusCode, username, password);
			Order order = new Order(configuration, jsonDirectory+json);
			Response orderResponse = order.userSelfOrders(auth, orderStatusCode);
			hardAssert.assertEquals(orderResponse.jsonPath().getString("errorCode"), errorCode, "Error code");
		}
		catch(Exception e){
			Reporter.log("Check message with existing skuid  failed: " + e.getMessage());
		}
	}
	/**
	 * Check message passing bad parameters
	 * @param json
	 * @param authStatusCode
	 * @param orderStatusCode
	 * @param errorCode
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "json", "authStatusCode","orderStatusCode","errorCode","username", "password" })
	public void checkMessageBadParameter(String json, String authStatusCode, String orderStatusCode, String errorCode,  String username, String password){
		try{
			Reporter.log("Check message passing bad parameters test steps:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			auth.loginByPassword(authStatusCode, username, password);
			Order order = new Order(configuration, jsonDirectory+json);
			Response orderResponse = order.userSelfOrders(auth, orderStatusCode);
			hardAssert.assertEquals(orderResponse.jsonPath().getString("errorCode"), errorCode);
		}
		catch(Exception e){
			Reporter.log("Check message passing bad parameters failed: " + e.getMessage());
		}
	}
	/**
	 * Check message passing incorrect email
	 * @param json
	 * @param authStatusCode
	 * @param userStatusCode
	 * @param errorCode
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "json","authStatusCode","userStatusCode","errorCode","username", "password" })
	public void checkMessageBadEmail(String json, String authStatusCode, String userStatusCode, String errorCode,  String username, String password){
		try{
			Reporter.log("Check message passing bad email test steps:");
			Authenticate auth = new Authenticate(configuration);
			User user = new User(configuration, jsonDirectory+json);
			auth.loginByClientCredentials(authStatusCode);
			Response response = user.createUser(auth,userStatusCode, username, password);
			hardAssert.assertEquals(response.jsonPath().getString("errorCode"), errorCode);
			hardAssert.assertEquals(response.jsonPath().getString("errors.key"), "[login.Email]");
		}
		catch(Exception e){
			Reporter.log("Check message passing bad email failed: " + e.getMessage());
		}
	}
	/**
	 * Check message when try register user with empty email 
	 * @param json
	 * @param authStatusCode
	 * @param userStatusCode
	 * @param errorCode
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "json","authStatusCode","userStatusCode","errorCode","username", "password" })
	public void checkMessageEmptyEmail(String json, String authStatusCode, String userStatusCode, String errorCode,  String username, String password){
		try{
			Reporter.log("Check message passing bad email test steps:");
			Authenticate auth = new Authenticate(configuration);
			User user = new User(configuration, jsonDirectory+json);
			auth.loginByClientCredentials(authStatusCode);
			Response response = user.createUser(auth,userStatusCode, username, password);
			softAssert.assertEquals(response.jsonPath().getString("errorCode"), errorCode, "Error code");
			softAssert.assertEquals(response.jsonPath().getString("errors.key"), "[login.Email]", "Error key");
			softAssert.assertAll();
		}
		catch(Exception e){
			Reporter.log("Check message passing bad email failed: " + e.getMessage());
		}
	}
	/**
	 * Check message when try t login with incorrect password
	 * @param statusCode
	 * @param errorCode
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({"statusCode", "errorCode", "username","password"})
	public void checkMessageLoginWithInvalidPassword(String statusCode, String errorCode,  String username, String password){
		try{
			Reporter.log("Check message with invalid password:");
			Authenticate auth = new Authenticate(configuration);
			Response authenticationResponse= auth.loginByPasswordFail(Integer.parseInt(statusCode), username, password);
			hardAssert.assertEquals(authenticationResponse.jsonPath().getString("errorCode"), errorCode, "Error code");
		}
		catch(Exception e){
			Reporter.log("Check message with invalid password: " + e.getMessage());
		}
	}
}