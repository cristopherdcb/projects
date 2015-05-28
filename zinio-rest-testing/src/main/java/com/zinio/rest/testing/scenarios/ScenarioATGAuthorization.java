package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zinio.rest.testing.actions.Atg;
/**
 * Scenarios for ATG authorization
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioATGAuthorization extends Scenario{
	/**
	 * Login through ATG by password
	 * @param statusCode
	 * @param clientId
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({ "statusCode", "clientId", "username"})
	public void loginByPassword(String statusCode, String clientId, String username, String password)
	{
		try	{
			Reporter.log("ATG login by password test steps:");
			Atg atg = new Atg(configuration);
			atg.loginByPassword(statusCode, clientId, username, password);
		}
		catch(Exception e){
			Reporter.log("ATG login by password failed: " + e.getMessage());
		}
	}
	/**
	 * Login through ATG by credentials
	 * @param statusCode
	 * @param clientId
	 */
	@Test
	@Parameters({"statusCode","clientId" })
	public void loginByCredentials(String statusCode, String clientId){
		try{
			Reporter.log("ATG login by credentials test steps:");
			Atg atg = new Atg(configuration);
			atg.loginByCredential(statusCode, clientId);
		}
		catch(Exception e){
			Reporter.log("ATG login by credentials failed: " + e.getMessage());
		}
	}
	/**
	 * Login through ATG by proxy
	 * @param statusCode
	 * @param clientId
	 * @param username
	 */
	@Test
	@Parameters({ "statusCode", "clientId", "username"})
	public void loginByInternalProxy(String statusCode, String clientId, String username)
	{
		try	{
			Reporter.log("ATG login by internal proxy test steps:");
			Atg atg = new Atg(configuration);
			atg.loginByInternalProxy(statusCode, clientId, username);
		}
		catch(Exception e){
			Reporter.log("ATG login by internal proxy failed: " + e.getMessage());
		}
	}
	/**
	 * Login through ATG by third party
	 * @param statusCode
	 * @param fbAccessToken
	 * @param fbId
	 * @param fbExpirationDate
	 * @param clientId
	 */
	@Test
	@Parameters({ "statusCode","fbAccessToken","fbId","fbExpirationDate", "clientId"})
	public void loginByThirdParty(String statusCode, String fbAccessToken, String fbId, String fbExpirationDate, String clientId)
	{
		try	{
			Reporter.log("ATG login by third party test steps:");
			Atg atg = new Atg(configuration);
			atg.loginByThirdParty(statusCode, fbAccessToken, fbId, fbExpirationDate, clientId);
		}
		catch(Exception e){
			Reporter.log("ATG login by third party failed: " + e.getMessage());
		}
	}
}