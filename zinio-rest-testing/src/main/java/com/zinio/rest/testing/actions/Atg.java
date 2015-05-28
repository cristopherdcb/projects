package com.zinio.rest.testing.actions;

import org.testng.Reporter;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
import static com.jayway.restassured.RestAssured.given;
/**
 * Implements actions related to authenticate through ATG
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Atg extends Action{
	/**
	 * Token type in general should be bearer
	 */
	private String tokenType;
	/**
	 * Token provided by WSA
	 */
	private String accessToken;
	/**
	 * ATG constructor
	 * @param configuration
	 * @param configProperties
	 * @param dataTestProperties
	 */
	public  Atg(Configuration configuration){
		super(configuration);
	}
	/**
	 * Executes login in ATG by password
	 * @return response
	 */
	public Response loginByPassword(String statusCode, String clientId, String username, String password) {
		Reporter.log("- GET token throug ATG by password");
		Reporter.log("POST to "+ configuration.getWebUrl()+"api/token?grant_type=password"+"&clientId="+clientId+"&username="+username+"&password="+password);
		Response response = 
		given().
	    when().
	       post(configuration.getWebUrl()+"api/token?grant_type=password"+"&clientId="+clientId+"&username="+username+"&password="+password);
		performCommonAssertion(response, statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertNotNull(response.jsonPath().getString("user_id"), "User id cannot be null");
		softAssert.assertAll();
		hardAssert.assertEquals(response.statusCode(), statusCode);
		this.accessToken = response.jsonPath().getString("access_token");
		this.tokenType = response.jsonPath().getString("token_type");
		return response;
	}
	/**
	 * Executes login in ATG by credentials
	 * @param statusCode
	 * @param clientId
	 * @return response
	 */
	public Response loginByCredential(String statusCode, String clientId) {
		Reporter.log("- GET token throug ATG by credential");
		Reporter.log("POST to "+ configuration.getWebUrl()+"api/token?grant_type=client_credentials&clientId="+clientId);
		Response response = 
		given().
	    when().
	       post(configuration.getWebUrl()+"api/token?grant_type=client_credentials&clientId="+clientId);	
		performCommonAssertion(response, statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		this.accessToken = response.jsonPath().getString("access_token");
		this.tokenType = response.jsonPath().getString("token_type");
		return response;
	}
	/**
	 * Executes login in ATG by internal proxy
	 * @param statusCode
	 * @param username
	 * @param clientId
	 * @return response
	 */
	public Response loginByInternalProxy(String statusCode, String clientId, String username) {
		Reporter.log("- GET token throug ATG by internal proxy");
		Reporter.log("POST to "+ configuration.getWebUrl()+"api/token?grant_type=internal_proxy"+"&clientId="+clientId+"&username="+username);
		Response response = 
		given().
	    when().
	       post(configuration.getWebUrl()+"api/token?grant_type=internal_proxy"+"&clientId="+clientId+"&username="+username);	
		performCommonAssertion(response, statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		this.accessToken = response.jsonPath().getString("access_token");
		this.tokenType = response.jsonPath().getString("token_type");
		return response;
	}
	/**
	 * Executes login in ATG by third party
	 * @param statusCode
	 * @param username
	 * @param clientId
	 * @return response
	 */
	public Response loginByThirdParty(String statusCode, String fbAccessToken, String fbId, String fbExpirationDate, String clientId) {
		Reporter.log("- GET token throug ATG by third party");
		Reporter.log("POST to " + configuration.getWebUrl()+"api/token?grant_type=third_party"+"&clientId="+clientId+"&fb_access_token="+fbAccessToken+"&fb_id="+fbId+"&fb_expiration_date="+fbExpirationDate);
		Response response = 
		given().
	    when().
	       post(configuration.getWebUrl()+"api/token?grant_type=third_party"+"&clientId="+clientId+"&fb_access_token="+fbAccessToken+"&fb_id="+fbId+"&fb_expiration_date="+fbExpirationDate);	
		performCommonAssertion(response, statusCode);
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertAll();
		this.accessToken = response.jsonPath().getString("access_token");
		this.tokenType = response.jsonPath().getString("token_type");
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