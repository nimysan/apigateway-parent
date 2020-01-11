package com.platenogroup.apigateway.portal.interfaces.dto.api;

public class ApiDto {

	public ApiDto(String name, String description, String supportMethods, String destHost, String destPath,
			String proxyMehtod) {
		this.name = name;
		this.description = description;
		this.supportMethods = supportMethods;
		this.destHost = destHost;
		this.destPath = destPath;
		this.proxyMethod = proxyMehtod;
	}

	private String name;
	private String description;
	/**
	 * 逗号分隔的HTTP Method
	 */
	private String supportMethods;

	/**
	 * RouteDefinition
	 * 
	 * @param description
	 */
	private String destHost;
	private String destPath;
	private String proxyMethod;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupportMethods() {
		return supportMethods;
	}

	public void setSupportMethods(String supportMethods) {
		this.supportMethods = supportMethods;
	}

	public String getDestHost() {
		return destHost;
	}

	public void setDestHost(String destHost) {
		this.destHost = destHost;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public String getProxyMethod() {
		return proxyMethod;
	}

	public void setProxyMethod(String proxyMethod) {
		this.proxyMethod = proxyMethod;
	}

}
