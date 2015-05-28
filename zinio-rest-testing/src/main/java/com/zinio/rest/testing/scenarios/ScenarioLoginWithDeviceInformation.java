package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.actions.Authenticate;
import java.util.UUID;
/**
 * Scenario to login using device information
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioLoginWithDeviceInformation extends Scenario{
	
	/**
	 * Checks if first login with device id and installation id returns device information
	 * @param json
	 * @param statusCode
	 * @param username
	 * @param password
	 * @param deviceId
	 * @param installationId
	 */
	@Test
	@Parameters({ "json", "statusCode", "username", "password", "deviceId", "installationId"})
	public void firstLogin(String json, String statusCode, String username, String password, String deviceId, String installationId)
	{
		try	{
			Reporter.log("First login with device information:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			Response authResponseFirst = auth.loginByPasswordWithDeviceInfo(statusCode, username, password, deviceId, installationId);
			Response authResponseSecond = auth.loginByPasswordWithDeviceInfo(statusCode, username, password, deviceId, installationId);
			softAssert.assertEquals(authResponseSecond.jsonPath().getString("user_id"), authResponseFirst.jsonPath().getString("user_id"), "User id should be the same");
			softAssert.assertEquals(authResponseSecond.jsonPath().getString("device_id"), authResponseFirst.jsonPath().getString("device_id"), "Device id should be the same");
		}
		catch(Exception e){
			Reporter.log("First login with device information: " + e.getMessage());
		}
	}
	/**
	 * Checks same login with different device information
	 * @param json
	 * @param statusCode
	 * @param username
	 * @param password
	 * @param deviceId
	 */
	@Test
	@Parameters({ "json", "statusCode", "username", "password", "deviceId"})
	public void differentInstallationID(String json, String statusCode, String username, String password, String deviceId)
	{
		try	{
			Reporter.log("First login with device information:");
			Authenticate auth = new Authenticate(configuration, jsonDirectory+json);
			String firstInstallationId = UUID.randomUUID().toString();
			String secondInstallationId = UUID.randomUUID().toString();
			Response authResponseFirst = auth.loginByPasswordWithDeviceInfo(statusCode, username, password, deviceId, firstInstallationId);
			Response authResponseSecond = auth.loginByPasswordWithDeviceInfo(statusCode, username, password, deviceId, secondInstallationId);
			softAssert.assertEquals(authResponseSecond.jsonPath().getString("user_id"), authResponseFirst.jsonPath().getString("user_id"), "User id should be the same");
			softAssert.assertEquals(authResponseSecond.jsonPath().getString("device_id"), authResponseFirst.jsonPath().getString("device_id"), "Device id should be the same");
		}
		catch(Exception e){
			Reporter.log("First login with device information: " + e.getMessage());
		}
	}
}
