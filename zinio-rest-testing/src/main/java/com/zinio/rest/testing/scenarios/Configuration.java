package com.zinio.rest.testing.scenarios;
/**
 * Implements configuration environment such as authorization URL,
 * API URL, SEARCH URL, client ID and client secret
 * @author Cristopher Castillo
 */
public class Configuration {
	/**
	 * Authorization URL
	 */
	private String authUrl;
	/**
	 * API URL 
	 */
	private String apiUrl;
	/**
	 * Client id
	 */
	private String clientId;
	/**
	 * Client secret
	 */
	private String clientSecret;
	/**
	 * Web URL
	 */
	private String webUrl;
	/**
	 * Search URL
	 */
	private String searchUrl;
	/**
	 * Username 
	 */
	private String username;
	/**
	 * Password
	 */
	private String password;
	/**
	 * Configuration constructor
	 * @param authUrl
	 * @param apiUrl
	 * @param clientId
	 * @param clientSecret
	 */
	Configuration(String authUrl, String apiUrl, String clientId, String clientSecret,String webUrl, String searchUrl, String username, String password){
		this.authUrl=authUrl;
		this.apiUrl=apiUrl;
		this.clientId=clientId;
		this.clientSecret=clientSecret;
		this.webUrl=webUrl;
		this.searchUrl= searchUrl;
		this.username=username;
		this.password=password;
	}
	/**
	 * Gets authorization URL
	 * @return authUrl
	 */
	public String getAuthUrl() {
		return authUrl;
	}
	/**
	 * Sets authorization URL
	 * @param authUrl
	 */
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	/**
	 * Gets API URL
	 * @return apiUrl
	 */
	public String getApiUrl() {
		return apiUrl;
	}
	/**
	 * Sets API URL
	 * @param apiUrl
	 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	/**
	 * Gets client ID
	 * @return clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * Sets client ID
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * Gets client secret
	 * @return clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	/**
	 * Sets client secret
	 * @param clientSecret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	/**
	 * Gets web URL
	 * @return webUrl
	 */
	public String getWebUrl() {
		return webUrl;
	}
	/**
	 * Sets web URL
	 * @param webUrl
	 */
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	/**
	 * Gets username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Gets password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Gets search URL
	 * @return searchUrl
	 */
	public String getSearchUrl() {
		return searchUrl;
	}
	/**
	 * Sets search URL
	 * @param searchUrl
	 */
	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
}