package com.zinio.rest.testing.actions;

import java.io.IOException;
import org.testng.Reporter;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
/**
 * Implements actions related to issue end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Issue extends Action{
	/**
	 * Issue constructor
	 * @param configuration
	 */
	public  Issue(Configuration configuration){
		super(configuration);
	}
	/**
	 * Gets issue content descriptor 
	 * @return response
	 * @throws IOException 
	 */
	public Response getIssueContentDescriptor(Authenticate auth, String statusCode, String issueId, String platformId) throws IOException {
		Reporter.log("- GET contentDescriptor for issue: "+issueId+ " paltform: " + platformId);
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
	    when().
	       get(configuration.getApiUrl()+"issues/"+issueId+"/contentDescriptor?q=platform_id:"+platformId);	
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertEquals(response.statusCode(),Integer.parseInt(statusCode));	
		softAssert.assertAll();
		return response;
	}
}
