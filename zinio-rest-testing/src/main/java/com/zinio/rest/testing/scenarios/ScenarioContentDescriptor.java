package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zinio.rest.testing.actions.Authenticate;
import com.zinio.rest.testing.actions.Issue;
/**
 * Scenarios for content descriptor
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioContentDescriptor extends Scenario{
	/**
	 * Check content descriptor
	 * @param statusCode
	 * @param issueId
	 * @param platformId
	 * @param username
	 * @param password
	 */
	@Test
	@Parameters({"statusCode","issueId","platformId","username", "password"})
	public void checkContentDescriptor(String statusCode, String issueId, String platformId, String username, String password)
	{
		try	{
			Reporter.log("Check content descriptor test steps:");
			Authenticate auth = new Authenticate(configuration);
			auth.loginByPassword(statusCode, username, password);
			Issue issue = new Issue(configuration);
			issue.getIssueContentDescriptor(auth, statusCode, issueId, platformId);
		}
		catch(Exception e){
			e.printStackTrace();
			Reporter.log("Check content descriptor: " + e.getMessage());
		}
	}
}