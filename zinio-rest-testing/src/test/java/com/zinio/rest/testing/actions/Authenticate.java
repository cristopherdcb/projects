package com.zinio.rest.testing.actions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.Reporter;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
import static com.jayway.restassured.RestAssured.given;
/**
 * Implements actions related to authenticate end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Authenticate extends Action{
	/**
	 * Token type in general should be bearer
	 */
	private String tokenType;
	/**
	 * Token provided by WSA
	 */
	private String accessToken;
	/**
	 * Path to json file
	 */
	private String jsonPath;
	/**
	 * Authenticate constructor
	 * @param configuration
	 * @param jsonPath
	 */
	public  Authenticate(Configuration configuration,  String jsonPath){
		super(configuration);
		this.jsonPath=jsonPath;
	}
	/**
	 * Authenticate constructor
	 * @param configuration
	 */
	public  Authenticate(Configuration configuration){
		super(configuration);
	}
	/**
	 * Executes login by password
	 * @return response
	 */
	public Response loginByPassword(String statusCode, String username, String password) {
		Reporter.log("- POST to authenticate by password");
		Response response = 
		given().
			param("grant_type", "password").
			param("client_id", configuration.getClientId()).
			param("client_secret", configuration.getClientSecret()).
			param("username", username).
			param("password", password).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		performCommonAssertion(response,statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		tokenType = response.jsonPath().getString("token_type");
		accessToken = response.jsonPath().getString("access_token");
		return response;
	}
	/**
	 * Executes login by password failing status
	 * @return response
	 */
	public Response loginByPasswordFail(int statusCode, String username, String password) {
		Reporter.log("- POST to authenticate by password");
		Response response = 
		given().
			param("grant_type", "password").
			param("client_id", configuration.getClientId()).
			param("client_secret", configuration.getClientSecret()).
			param("username", username).
			param("password", password).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		Reporter.log("Response: " + response.prettyPrint());
		return response;
	}
	/**
	 * Executes login by client credentials
	 * @return response
	 */
	public Response loginByClientCredentials(String statusCode) {
		Reporter.log("- POST to authenticate by client_credentials");
		Response response = 
		given().
			param("grant_type", "client_credentials").
			param("client_id", configuration.getClientId()).
			param("client_secret", configuration.getClientSecret()).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		performCommonAssertion(response,statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		tokenType = response.jsonPath().getString("token_type");
		accessToken = response.jsonPath().getString("access_token");
		return response;
	}
	/**
	 * Executes login by internal PROXY
	 * @return response
	 */
	public Response loginByInternalProxy(String statusCode, String username) {
		Reporter.log("- POST to authenticate by internal_proxy");
		Response response = 
		given().
			param("grant_type", "internal_proxy").
			param("client_id", configuration.getClientId()).
			param("client_secret", configuration.getClientSecret()).
			param("username", username).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		performCommonAssertion(response,statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		tokenType = response.jsonPath().getString("token_type");
		accessToken = response.jsonPath().getString("access_token");
		return response;
	}
	/**
	 * Executes login by third party Facebook
	 * @return response
	 * @throws IOException 
	 */
	public Response loginByThirdPartyFacebook(String statusCode, String accesToken, String id, String expirationDate, String jsonPath) throws IOException {
		Reporter.log("- POST to authenticate by third_party");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		body = body.replace("client_id_value", configuration.getClientId());
		body = body.replace("client_secret_value", configuration.getClientSecret());
		body = body.replace("access_token_value", accesToken);
		body = body.replace("id_value", id);
		body = body.replace("expiration_date_value", expirationDate);
		Response response = 
		given().
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		performCommonAssertion(response,statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		tokenType = response.jsonPath().getString("token_type");
		accessToken = response.jsonPath().getString("access_token");
		return response;
	}
	/**
	 * Executes login by third party Recorder books
	 * @return response
	 * @throws IOException 
	 */
	public Response loginByThirdPartyRecorderbook(String statusCode, String username, String id, String expirationDate) throws IOException {
		Reporter.log("- POST to authenticate by third_party");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		body = body.replace("client_id_value", configuration.getClientId());
		body = body.replace("client_secret_value", configuration.getClientSecret());
		body = body.replace("id_value", id);
		body = body.replace("expiration_date_value", expirationDate);
		body = body.replace("username_value", username);
		Response response = 
		given().
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		performCommonAssertion(response,statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		tokenType = response.jsonPath().getString("token_type");
		accessToken = response.jsonPath().getString("access_token");
		return response;
	}
	/**
	 * Executes login providing device information
	 * @param statusCode
	 * @return response
	 * @throws IOException
	 */
	public Response loginByPasswordWithDeviceInfo(String statusCode, String username, String password, String deviceId, String installationId) throws IOException {
		Reporter.log("- POST to authenticate providing device info");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		body = body.replace("client_id_value", configuration.getClientId());
		body = body.replace("client_secret_value", configuration.getClientSecret());
		body = body.replace("username_value", username);
		body = body.replace("password_value", password);
		body = body.replace("device_id_value", deviceId);
		body = body.replace("installation_id_value", installationId);
		Response response = 
		given().
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getAuthUrl()+"authenticate");	
		performCommonAssertion(response,statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertNotNull(response.jsonPath().getString("user_id"),"User id should't be null");
		softAssert.assertNotNull(response.jsonPath().getString("device_id"),"Device id should't be null");
		softAssert.assertAll();
		tokenType = response.jsonPath().getString("token_type");
		accessToken = response.jsonPath().getString("user_id");
		return response;
	}
	/**
	 * Performs assertion for common attributes in response 
	 * @param response
	 * @param statusCode
	 */
	private void performCommonAssertion(Response response, String statusCode){
		hardAssert.assertNotNull(response, "Response cannot be null");
		softAssert.assertEquals(response.statusCode(),Integer.parseInt(statusCode));
		softAssert.assertNotNull(response.jsonPath().getString("token_type"),"Token type shouldn’t be null");
		softAssert.assertNotNull(response.jsonPath().getString("access_token"),"Access token shouldn’t be null");
		softAssert.assertNotNull(response.jsonPath().getString("expires_in"),"Expiration token shouldn’t be null");
	}
	/**
	 * Gets token type
	 * @return
	 */
	public String getTokenType() {
		return tokenType;
	}
	/**
	 * Gets access token
	 * @return accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * Gets authorization which is token type concatenated with access token
	 * @return
	 */
	public String getAuthorization(){
		return tokenType+" "+accessToken;
	}
}