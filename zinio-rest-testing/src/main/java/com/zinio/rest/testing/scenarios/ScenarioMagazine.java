package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.actions.Authenticate;
import com.zinio.rest.testing.actions.Magazine;
/**
 * Scenarios for magazines
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioMagazine extends Scenario{
	/**
	 * Checks if auto renew flag is true, if so, then auto renew type must be MANDATORY or OPTIONAL
	 * otherwise auto renew type shouldn't be present
	 * @param statusCode
	 * @param magazineId
	 */
	@Test
	@Parameters({"statusCode", "magazineId"})
	public void checkAutoRenewType(String statusCode, String magazineId)
	{
		try	{
			Reporter.log("Checking autorenew type:");
			Authenticate auth = new Authenticate(configuration);
			auth.loginByClientCredentials(statusCode);
			Magazine magazine = new Magazine(configuration);
			Response magazineResponse = magazine.magazineById(auth, statusCode, magazineId);
			String autoRenew = magazineResponse.jsonPath().getString("defaultOffer.autoRenew");
			Reporter.log(magazineResponse.jsonPath().getString("defaultOffer.autoRenew")); 
			if(autoRenew.equals("true")){
				softAssert.assertNotNull(magazineResponse.jsonPath().get("defaultOffer.autoRenewType"), "Auto renew type should be present");
				softAssert.assertTrue(magazineResponse.jsonPath().get("defaultOffer.autoRenewType").equals("MANDATORY") || magazineResponse.jsonPath().get("defaultOffer.autoRenewType").equals("OPTIONAL"), "Auto renew type should be MANDATORY or OPTIONAL");
				softAssert.assertAll();
			}
			else if(autoRenew.equals("false")){
				softAssert.assertNull(magazineResponse.jsonPath().get("defaultOffer.autoRenewType"), "Auto renew type shouldn't be present");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			Reporter.log("Checking if autorenew type is present: " + e.getMessage());
		}
	}
}