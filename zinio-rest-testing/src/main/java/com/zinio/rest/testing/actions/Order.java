package com.zinio.rest.testing.actions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.response.Response;
import com.zinio.rest.testing.scenarios.Configuration;
/**
 * Implements actions related to orders end point
 * @author Cristopher Castillo
 * @see com.zinio.rest.testing.Action
 */
public class Order extends Action{
	/**
	 * Path to json file
	 */
	private String jsonPath;
	/**
	 * Authenticate constructor
	 * @param configuration
	 * @param jsonPath
	 */
	public  Order(Configuration configuration, String jsonPath){
		super(configuration);
		this.jsonPath=jsonPath;
	}
	/**
	 * Executes orders prepare
	 * @param auth
	 * @param statusCode
	 * @return response
	 * @throws IOException
	 */
	public Response ordersPrepare(Authenticate auth, String statusCode) throws IOException {
		Reporter.log("- POST to orders/prepare");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getApiUrl()+"orders/prepare");
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		softAssert.assertEquals(response.statusCode(), Integer.parseInt(statusCode), "Status code");
		softAssert.assertAll();
		return response;
	}
	/**
	 * Executes self order creation 
	 * @param auth
	 * @param statusCode
	 * @return response
	 * @throws IOException
	 */
	public Response userSelfOrders(Authenticate auth, String statusCode) throws IOException {
		Reporter.log("- POST to users/self/orders");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getApiUrl()+"users/self/orders");	
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		return response;
	}
	/**
	 * Executes self delivery orders creation 
	 * @param auth
	 * @param statusCode
	 * @return response
	 * @throws IOException
	 */
	public Response userSelfDeliveryOrders(Authenticate auth, String statusCode, String type, String numberOfIssues, String skuId) throws IOException {
		Reporter.log("- POST to users/self/deliveryOrders");
		File json = new File(jsonPath);
		hardAssert.assertNotNull(json,jsonPath+" couldn't be found");
		String body=FileUtils.readFileToString(json);
		body = body.replace("type_value", type);
		body = body.replace("number_of_issues_value", numberOfIssues);
		body = body.replace("sku_id_value", skuId);
		Response response = 
		given().
			header("Authorization",auth.getAuthorization()).
			header("Content-Type","application/json").
			body(body).
	    when().
	       post(configuration.getApiUrl()+"users/self/deliveryOrders");	
		hardAssert.assertNotNull(response, "Response cannot be null");
		Reporter.log("Response: " + response.prettyPrint());
		return response;
	}
}
