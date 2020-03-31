package com.platenogroup.apigateway.portal.interfaces.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiRequestDto {
	private String name;
	private String accessPath;
	private String description;
	private String apiVersion;
}
