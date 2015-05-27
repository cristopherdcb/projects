package com.zinio.rest.testing.scenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
/**
 * Parent class which sets general properties for test
 * @author Cristopher Castillo
 */
public class Scenario {
	/**
	 * Environment configuration
	 */
	protected static Configuration configuration;
	/**
	 * Contain data test for scenarios   
	 */
	protected static Properties environmentProperties;
	/**
	 * Path to JSON directory
	 */
	protected static String jsonDirectory; 
	/**
	 * Hard assertions that will breaks test execution
	 */
	protected Assertion hardAssert = new Assertion();
	/**
	 * Soft assertions that will be notified but it will not interrupt test execution
	 */
	protected SoftAssert softAssert = new SoftAssert();
	/**
	 * Constant to identify DEV environment
	 */
	private static final String DEV = "dev";
	/**
	 * Constant to identify STG environment
	 */
	private static final String STG = "stg";
	/**
	 * Constant to identify PROD environment
	 */
	private static final String PROD = "prod";
	/**
	 * Set general properties before starting test
	 * @param testContext
	 */
	@BeforeSuite(alwaysRun = true)
	public void setSuite(final ITestContext testContext)
	{
		try	{
			Reporter.log(testContext.getCurrentXmlTest().getSuite().getName());
			setEnvironment();
			jsonDirectory="test-classes"+System.getProperty("file.separator")+"test-data"+System.getProperty("file.separator");
		}
		catch(Exception e){
			Reporter.log("The REST test couldn't be launched: " + e.getMessage());
		}
	}
	/**
	 * Set configuration according to environment
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertiesFormatException 
	 */
	private void setEnvironment() throws InvalidPropertiesFormatException, FileNotFoundException, IOException{
		environmentProperties = new Properties();
		if(System.getProperty("environment").equals(DEV)){
			environmentProperties.loadFromXML(new FileInputStream("test-classes"+System.getProperty("file.separator")+"conf"+System.getProperty("file.separator")+DEV+"-properties.xml"));
		}
		else if(System.getProperty("environment").equals(STG)){
			environmentProperties.loadFromXML(new FileInputStream("test-classes"+System.getProperty("file.separator")+"conf"+System.getProperty("file.separator")+STG+"-properties.xml"));
		}
		else if(System.getProperty("environment").equals(PROD)){
			environmentProperties.loadFromXML(new FileInputStream("test-classes"+System.getProperty("file.separator")+"conf"+System.getProperty("file.separator")+PROD+"-properties.xml"));
		}
		else{
			environmentProperties.loadFromXML(new FileInputStream("test-classes"+System.getProperty("file.separator")+"conf"+System.getProperty("file.separator")+STG+"-properties.xml"));
		}
		configuration = new Configuration(environmentProperties.getProperty("authUrl"),
										  environmentProperties.getProperty("apiUrl"),
										  environmentProperties.getProperty("clientId"),
										  environmentProperties.getProperty("clientSecret"),
										  environmentProperties.getProperty("webUrl"),
										  environmentProperties.getProperty("searchUrl"),
										  environmentProperties.getProperty("username"),
										  environmentProperties.getProperty("password"));
	}
}