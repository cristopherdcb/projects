package com.zinio.rest.testing.actions;

import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import com.zinio.rest.testing.scenarios.Configuration;
/**
 * Sets commons elements for actions
 * @author Cristopher Castillo
 */
public class Action {
	/**
	 * Environment configuration
	 */
	protected Configuration configuration;
	/**
	 * Hard assertions that will breaks test execution
	 */
	protected Assertion hardAssert = new Assertion();
	/**
	 * Soft assertions that will be notified but it will not interrupt test execution
	 */
	protected SoftAssert softAssert = new SoftAssert();
	/**
	 * Action constructor
	 * @param configuration
	 */
	public Action(Configuration configuration){
		this.configuration = configuration;
	}
}