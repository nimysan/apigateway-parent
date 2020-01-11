package com.platenogroup.apigateway.portal.interfaces.dto.api;

public class ApiRespDto {
	
	private String name;
	private String id;
	private String description;
	private String downstreamDefinition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDownstreamDefinition() {
		return downstreamDefinition;
	}

	public void setDownstreamDefinition(String downstreamDefinition) {
		this.downstreamDefinition = downstreamDefinition;
	}

}
