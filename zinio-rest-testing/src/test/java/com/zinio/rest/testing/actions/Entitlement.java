package com.zinio.rest.testing.actions;

import java.util.List;

import org.testng.Reporter;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
/**
 * Implements actions related to entitlement end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Entitlement extends Action{
	
	/**
	 * Authenticate constructor
	 * @param configuration
	 */
	public  Entitlement(Configuration configuration){
		super(configuration);
	}
	/**
	 * Archive single entitlement
	 * @param auth
	 * @param statusCode
	 * @param entitlementId
	 * @return response
	 */
	public Response archiveSingleEntitlement(Authenticate auth, String statusCode, String entitlementId){
		Reporter.log("- POST to entitlements/archive");
		Reporter.log("- Archiving entitlement " + entitlementId);
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
	    when().
	       post(configuration.getApiUrl()+"entitlements/"+entitlementId+"/archive");
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		softAssert.assertAll();
		return response;
	}
	/**
	 * Archive multiple entitlements
	 * @param auth
	 * @param statusCode
	 * @param entitlementsId
	 * @return response
	 */
	public Response archiveMultipleEntitlement(Authenticate auth, String statusCode, List<String> entitlementsId){
		Reporter.log("- POST to entitlements/archive");
		Reporter.log("- Archiving entitlement " + entitlementsId.toString());
		String entitlements = entitlementsId.toString();
		entitlements = entitlements.replace("[", "[\"");
		entitlements = entitlements.replace("]", "\"]");
		entitlements = entitlements.replace(", ", "\",\"");
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
			header("Content-Type","application/json").
			body(entitlements).
	    when().
	       post(configuration.getApiUrl()+"entitlements/archive");
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		softAssert.assertAll();
		return response;
	}
	/**
	 * Restore single entitlement
	 * @param auth
	 * @param statusCode
	 * @param entitlementId
	 * @return response
	 */
	public Response restoreSingleEntitlement(Authenticate auth, String statusCode, String entitlementId){
		Reporter.log("- POST to entitlements/archive");
		Reporter.log("- Restoring entitlement " + entitlementId);
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
	    when().
	       post(configuration.getApiUrl()+"entitlements/"+entitlementId+"/restore");
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		softAssert.assertAll();
		return response;
	}
	/**
	 * Restore multiple entitlements
	 * @param auth
	 * @param statusCode
	 * @param entitlementsId
	 * @return response
	 */
	public Response restoreMultipleEntitlement(Authenticate auth, String statusCode, List<String> entitlementsId){
		Reporter.log("- POST to entitlements/archive");
		Reporter.log("- Restoring entitlement " + entitlementsId.toString());
		String entitlements = entitlementsId.toString();
		entitlements = entitlements.replace("[", "[\"");
		entitlements = entitlements.replace("]", "\"]");
		entitlements = entitlements.replace(", ", "\",\"");
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
			header("Content-Type","application/json").
			body(entitlements).
	    when().
	       post(configuration.getApiUrl()+"entitlements/restore");
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		softAssert.assertAll();
		return response;
	}
}