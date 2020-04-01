package com.platenogroup.apigateway.portal.domain.api;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ApiRouteDefinition {

	@Column
	private String upstream;
}
