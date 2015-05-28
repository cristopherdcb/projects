package com.zinio.rest.testing.actions;

import java.util.Random;

import org.testng.Reporter;

import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;

import static com.jayway.restassured.RestAssured.given;
/**
 * Implements actions related to search endpoint
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Search extends Action{

	/**
	 * Search constructor
	 * @param configuration
	 */
	public  Search(Configuration configuration){
		super(configuration);
	}
	/**
	 * Executes search
	 * @return response
	 */
	public Response search(String statusCode, String searchCriteria, String platformId) {
		if(searchCriteria==null){
			searchCriteria = generateRandomWord();
		}
		Reporter.log("- GET to search using criteria: " + searchCriteria + " and platform id: " + platformId);
		Response response = 
		given().
	    when().
	       get(configuration.getSearchUrl()+"title?q=" + searchCriteria+ "&platform_id="+platformId);	
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertNotNull(response.jsonPath().getString("term"), "Term");
		softAssert.assertNotNull(response.jsonPath().getString("total"), "Total");
		softAssert.assertNotNull(response.jsonPath().getString("size"), "Size");
		softAssert.assertNotNull(response.jsonPath().getString("hits"), "Hits");
		softAssert.assertAll();
		return response;
	}
	/**
	 * Generates random word to be used in find magazines or any other functionality
	 * @return randomWord
	 */
	private String generateRandomWord()
	{
	    Random random = new Random();	    
        char[] word = new char[1]; 
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
        String randomWord = new String(word);
	    return randomWord;
	}
}