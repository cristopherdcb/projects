package com.zinio.rest.testing.actions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;

import static com.jayway.restassured.RestAssured.given;
/**
 * Implements actions related to user end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class User extends Action{
	
	/**
	 * Path to json file
	 */
	private String jsonPath;
	/**
	 * User constructor
	 * @param configuration
	 */
	public  User(Configuration configuration){
		super(configuration);
	}
	/**
	 * User constructor
	 * @param configuration
	 * @param jsonPath
	 */
	public  User(Configuration configuration,  String jsonPath){
		super(configuration);
		this.jsonPath=jsonPath;
	}
	/**
	 * Executes user creation 
	 * @return response
	 * @throws IOException 
	 */
	public Response createUser(Authenticate auth, String statusCode, String login, String password) throws IOException {
		Reporter.log("- POST to user");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		body = body.replace("login_value", login);
		body = body.replace("password_value", password);
		Reporter.log(body);
		Response response = 
		given().			
			header("Authorization",auth.getAuthorization()).
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getApiUrl()+"users");
		hardAssert.assertNotNull(response,"Response shouldn't be null");
		Reporter.log("Response: " + response.prettyPrint());
		hardAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		return response;
	}
}