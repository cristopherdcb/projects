package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zinio.rest.testing.actions.Authenticate;
import com.zinio.rest.testing.actions.Order;
/**
 * Scenarios for campaign 
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioCampaing extends Scenario{
	
	/**
	 * Set general properties before starting test
	 */
	@Test
	@Parameters({ "json", "statusCode", "username", "password"})
	public void campaingOrderProcessing(String json, String statusCode, String username, String password)
	{
		try	{
			Reporter.log("Check credit card info encoded in base 64  test steps:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			auth.loginByPassword(statusCode, username, password);
			Order order = new Order(configuration,jsonDirectory+json);
			order.ordersPrepare(auth, statusCode);
			/*hardAssert.assertNotNull(orderResponse.jsonPath().getString("payments.ccToken"), "ccToken cannot be null");
			hardAssert.assertNotNull(orderResponse.jsonPath().getString("payments.cvvToken"),"cvvToken cannot be null");
			softAssert.assertEquals(orderResponse.jsonPath().getString("payments.ccToken"),creditCardNumber);
			softAssert.assertEquals(orderResponse.jsonPath().getString("payments.cvvToken"),cvv);
			softAssert.assertAll();*/
		}
		catch(Exception e){
			Reporter.log("Check credit card info encoded in base 64: " + e.getMessage());
		}
	}
}