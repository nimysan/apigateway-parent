package com.platenogroup.apigateway.portal.domain.model.api;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ApiRouteDefinition {

	@Column
	private String upstreamUrl;
}
