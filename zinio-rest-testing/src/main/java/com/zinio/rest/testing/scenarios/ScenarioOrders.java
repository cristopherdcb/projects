package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.actions.Authenticate;
import com.zinio.rest.testing.actions.Order;
/**
 * Scenarios for orders
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioOrders extends Scenario{
	
	/**
	 * Check credit card info encoded in base 64
	 * @param json
	 * @param statusCode
	 * @param creditCardNumber
	 * @param cvv
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "json", "statusCode", "creditCardNumber", "cvv", "username", "password"})
	public void checkEncodeBase64CreditCardInfo(String json, String statusCode, String creditCardNumber, String cvv, String username, String password)
	{
		try	{
			Reporter.log("Check credit card info encoded in base 64  test steps:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			auth.loginByPassword(statusCode, username, password);
			Order order = new Order(configuration,jsonDirectory+json);
			Response orderResponse = order.userSelfOrders(auth, statusCode);
			hardAssert.assertNotNull(orderResponse.jsonPath().getString("payments.ccToken"), "ccToken cannot be null");
			hardAssert.assertNotNull(orderResponse.jsonPath().getString("payments.cvvToken"),"cvvToken cannot be null");
			softAssert.assertEquals(orderResponse.jsonPath().getString("payments.ccToken"),creditCardNumber);
			softAssert.assertEquals(orderResponse.jsonPath().getString("payments.cvvToken"),cvv);
			softAssert.assertAll();
		}
		catch(Exception e){
			Reporter.log("Check credit card info encoded in base 64: " + e.getMessage());
		}
	}
}