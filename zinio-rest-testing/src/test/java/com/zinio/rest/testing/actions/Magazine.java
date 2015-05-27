package com.zinio.rest.testing.actions;

import java.io.IOException;
import org.testng.Reporter;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
import static com.jayway.restassured.RestAssured.given;
/**
 * Implements actions related to magazine end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Magazine extends Action{
	
	/**
	 * Magazine constructor
	 * @param configuration
	 */
	public  Magazine(Configuration configuration){
		super(configuration);
	}
	/**
	 * Executes call to magazines end-point by id 
	 * @return response
	 * @throws IOException 
	 */
	public Response magazineById(Authenticate auth, String statusCode, String magazineId) throws IOException {
		Reporter.log("- GET to magazines");
		Response response = 
		given().			
			header("Authorization",auth.getAuthorization()).
	    when().
	       get(configuration.getApiUrl()+"magazines/"+magazineId);
		hardAssert.assertNotNull(response,"Response shouldn't be null");
		Reporter.log("Response: " + response.prettyPrint());
		hardAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		return response;
	}
	/**
	 * Executes call to magazines end-point
	 * @return response
	 * @throws IOException 
	 */
	public Response magazine(Authenticate auth, String statusCode, String magazineId) throws IOException {
		Reporter.log("- GET to magazines");
		Response response = 
		given().			
			header("Authorization",auth.getAuthorization()).
	    when().
	       get(configuration.getApiUrl()+"magazines/");
		hardAssert.assertNotNull(response,"Response shouldn't be null");
		Reporter.log("Response: " + response.prettyPrint());
		hardAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		return response;
	}
}