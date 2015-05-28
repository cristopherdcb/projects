package com.zinio.rest.testing.scenarios;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zinio.rest.testing.actions.Search;
/**
 * Scenario for search endpoint
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioSearch extends Scenario{
	/**
	 * Perform search with random search criteria
	 * @param statusCode
	 * @param platformId
	 */
	@Test
	@Parameters({ "statusCode", "platformId"})
	public void search(String statusCode, String platformId)
	{
		try	{
			Reporter.log("Searching test steps:");
			Search search = new Search(configuration);
			search.search(statusCode, null, platformId);
		}
		catch(Exception e){
			Reporter.log("Searching failed: " + e.getMessage());
		}
	}
	/**
	 * Perform search with specific search criteria
	 * @param statusCode
	 * @param platformId
	 * @param searchCriteria
	 */
	@Test
	@Parameters({ "statusCode", "platformId","searchCriteria"})
	public void searchByCriteria(String statusCode, String platformId, String searchCriteria)
	{
		try	{
			Reporter.log("Searching test steps:");
			Search search = new Search(configuration);
			search.search(statusCode, searchCriteria, platformId);
		}
		catch(Exception e){
			Reporter.log("Searching failed: " + e.getMessage());
		}
	}
}