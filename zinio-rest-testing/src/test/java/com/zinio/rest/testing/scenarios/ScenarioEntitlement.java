package com.zinio.rest.testing.scenarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.actions.Authenticate;
import com.zinio.rest.testing.actions.Entitlement;
import com.zinio.rest.testing.actions.Library;
/**
 * Scenarios for entitlement endpoint
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Scenario
 */
public class ScenarioEntitlement extends Scenario{
	/**
	 * Archive and restore single entitlement 
	 * @param statusCode
	 * @param username
	 * @param password
	 * @param pageSize
	 */
	@Test
	@Parameters({"statusCode", "username", "password", "pageSize"})
	public void archiveSingleEntitlement(String statusCode, String username, String password, String pageSize)
	{
		try	{
			Reporter.log("Archiving single entitlement test steps:");
			Authenticate auth = new Authenticate(configuration);
			Library library = new Library(configuration);
			Entitlement entitlement = new Entitlement(configuration);
			auth.loginByPassword(statusCode, username, password);
			Response libraryResponseBeforeArchive =library.userSelfLibrary(auth,statusCode,pageSize);
			List<String> listEntitlementBeforeArchive = new ArrayList<String>();
			Random rand = new Random();
			listEntitlementBeforeArchive=libraryResponseBeforeArchive.jsonPath().getList("data.id");
			int randomEntitlementIndex = listEntitlementBeforeArchive.size()-1<=0 ? 0: rand.nextInt(listEntitlementBeforeArchive.size()-1);						
			String entitlementId = listEntitlementBeforeArchive.get(randomEntitlementIndex);
			entitlement.archiveSingleEntitlement(auth,statusCode, entitlementId);
			Response libraryResponseAfterArchive =library.userSelfLibrary(auth,statusCode,pageSize);
			List<String> listEntitlementAfterArchive = new ArrayList<String>();
			listEntitlementAfterArchive=libraryResponseAfterArchive.jsonPath().getList("data.id");
			softAssert.assertTrue(!listEntitlementAfterArchive.contains(entitlementId), "Shouldn't contains entitlement archived");
			entitlement.restoreSingleEntitlement(auth,statusCode, entitlementId);
			Response libraryResponseAfterRestore =library.userSelfLibrary(auth,statusCode,pageSize);
			List<String> listEntitlementAfterRestore = new ArrayList<String>();
			listEntitlementAfterRestore=libraryResponseAfterRestore.jsonPath().getList("data.id");
			softAssert.assertTrue(listEntitlementAfterRestore.contains(entitlementId), "Should contains entitlement restored");
			Reporter.log("Entitlement before archive "+libraryResponseBeforeArchive.jsonPath().getString("data.id"));
			Reporter.log("Entitlement after archive "+libraryResponseAfterArchive.jsonPath().getString("data.id"));
			Reporter.log("Entitlement after restore "+libraryResponseAfterRestore.jsonPath().getString("data.id"));
			softAssert.assertAll();
		}
		catch(Exception e){
			hardAssert.fail("Archiving single entitlement failed: " + e.getMessage());
		}
	}
	/**
	 * Archive and restore multiple entitlement
	 * @param statusCode
	 * @param username
	 * @param password
	 * @param pageSize
	 */
	@Test
	@Parameters({"statusCode", "username", "password", "pageSize"})
	public void archiveMultipleEntitlement(String statusCode, String username, String password, String pageSize)
	{
		try	{
			Reporter.log("Archiving multiple entitlement test steps:");
			Authenticate auth = new Authenticate(configuration);
			Library library = new Library(configuration);
			Entitlement entitlement = new Entitlement(configuration);
			auth.loginByPassword(statusCode, username, password);
			Response libraryResponseBeforeArchive =library.userSelfLibrary(auth,statusCode,pageSize);
			List<String> listEntitlementBeforeArchive = new ArrayList<String>();
			Random rand = new Random();
			listEntitlementBeforeArchive=libraryResponseBeforeArchive.jsonPath().getList("data.id");
			int randomEntitlementIndex = listEntitlementBeforeArchive.size()-1<=0 ? 0: rand.nextInt(listEntitlementBeforeArchive.size()-1);
			List<String> sublistEntitlementBeforeArchive = new ArrayList<String>();
			sublistEntitlementBeforeArchive = listEntitlementBeforeArchive.subList(0, randomEntitlementIndex);
			entitlement.archiveMultipleEntitlement(auth,statusCode, sublistEntitlementBeforeArchive);
			Response libraryResponseAfterArchive =library.userSelfLibrary(auth, statusCode, pageSize);
			List<String> listEntitlementAfterArchive = new ArrayList<String>();
			listEntitlementAfterArchive=libraryResponseAfterArchive.jsonPath().getList("data.id");
			softAssert.assertTrue(listEntitlementAfterArchive.containsAll(listEntitlementAfterArchive), "Shouldn't contains entitlements archived");
			entitlement.restoreMultipleEntitlement(auth,statusCode, sublistEntitlementBeforeArchive);
			Response libraryResponseAfterRestore =library.userSelfLibrary(auth, statusCode,pageSize);
			List<String> listEntitlementAfterRestore = new ArrayList<String>();
			listEntitlementAfterRestore=libraryResponseAfterRestore.jsonPath().getList("data.id");
			softAssert.assertTrue(listEntitlementAfterRestore.containsAll(sublistEntitlementBeforeArchive), "Should contains entitlement restored");
			Reporter.log("Entitlement before archive "+libraryResponseBeforeArchive.jsonPath().getString("data.id"));
			Reporter.log("Entitlement after archive "+libraryResponseAfterArchive.jsonPath().getString("data.id"));
			Reporter.log("Entitlement after restore "+libraryResponseAfterRestore.jsonPath().getString("data.id"));
			softAssert.assertAll();
		}
		catch(Exception e){
			Reporter.log("Archiving multiple entitlement failed: " + e.getMessage());
		}
	}
	/**
	 * Archive and restore single entitlement for recorder book
	 * @param statusCode
	 * @param username
	 * @param password
	 * @param pageSize
	 * @param expirationDate
	 * @param id
	 * @param json
	 * @param skuId
	 * @param numberOfIssues
	 * @param type
	 * @param deliveryJson
	 */
	@Test
	@Parameters({"statusCode", "username", "password", "pageSize", "expirationDate","id", "json", "skuId", "numberOfIssues", "type","deliveryJson"})
	public void archiveEntitlementThirdParty(String statusCode, String username, String password, String pageSize, String expirationDate, 
											 String id, String json, String skuId, String numberOfIssues, String type, String deliveryJson){
		try{
			Reporter.log("Archiving entitlement for third party:");
			Authenticate auth = new Authenticate(configuration,jsonDirectory+json);
			Library library = new Library(configuration);
			Entitlement entitlement = new Entitlement(configuration);
			auth.loginByThirdPartyRecorderbook(statusCode, username, id, expirationDate);
			Response libraryResponseBeforeArchive =library.userSelfLibrary(auth, statusCode,pageSize);
			List<String> listEntitlementBeforeArchive = new ArrayList<String>();
			Random rand = new Random();
			listEntitlementBeforeArchive=libraryResponseBeforeArchive.jsonPath().getList("data.id");
			int randomEntitlementIndex = listEntitlementBeforeArchive.size()-1<=0 ? 0: rand.nextInt(listEntitlementBeforeArchive.size()-1);						
			String entitlementId = listEntitlementBeforeArchive.get(randomEntitlementIndex);
			entitlement.archiveSingleEntitlement(auth,statusCode, entitlementId);
			Response libraryResponseAfterArchive =library.userSelfLibrary(auth, statusCode,pageSize);
			List<String> listEntitlementAfterArchive = new ArrayList<String>();
			listEntitlementAfterArchive=libraryResponseAfterArchive.jsonPath().getList("data.id");
			softAssert.assertTrue(!listEntitlementAfterArchive.contains(entitlementId), "Shouldn't contains entitlement archived");
			entitlement.restoreSingleEntitlement(auth,statusCode, entitlementId);
			Response libraryResponseAfterRestore =library.userSelfLibrary(auth, statusCode,pageSize);
			List<String> listEntitlementAfterRestore = new ArrayList<String>();
			listEntitlementAfterRestore=libraryResponseAfterRestore.jsonPath().getList("data.id");
			softAssert.assertTrue(listEntitlementAfterRestore.contains(entitlementId), "Should contains entitlement restored");
			Reporter.log("Entitlement before archive "+libraryResponseBeforeArchive.jsonPath().getString("data.id"));
			Reporter.log("Entitlement after archive "+libraryResponseAfterArchive.jsonPath().getString("data.id"));
			Reporter.log("Entitlement after restore "+libraryResponseAfterRestore.jsonPath().getString("data.id"));
			softAssert.assertAll();
		}
		catch(Exception e){
			Reporter.log("Archiving entitlement for third party: " + e.getMessage());
		}
	}
}