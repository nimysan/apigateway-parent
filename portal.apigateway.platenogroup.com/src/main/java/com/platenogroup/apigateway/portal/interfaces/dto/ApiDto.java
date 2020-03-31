package com.platenogroup.apigateway.portal.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ApiDto {

	private String name;
	private String accessPath;
	private String description;
	private String createdBy;
}
