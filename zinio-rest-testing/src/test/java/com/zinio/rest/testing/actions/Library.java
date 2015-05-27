package com.zinio.rest.testing.actions;

import java.io.IOException;
import org.testng.Reporter;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
import static com.jayway.restassured.RestAssured.given;
/**
 * Implements actions related to user end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Library extends Action{
	
	/**
	 * Library constructor
	 * @param configuration
	 */
	public  Library(Configuration configuration){
		super(configuration);
	}
	/**
	 * Executes call to user self library
	 * @return response
	 * @throws IOException 
	 */
	public Response userSelfLibrary(Authenticate auth, String statusCode, String pageSize) throws IOException {
		Reporter.log("- GET to user/self/library");
		Response response = 
		given().			
			header("Authorization",auth.getAuthorization()).
	    when().
	       get(configuration.getApiUrl()+"users/self/library?size="+pageSize);
		hardAssert.assertNotNull(response,"Response shouldn't be null");
		Reporter.log("Response: " + response.prettyPrint());
		hardAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		return response;
	}
}