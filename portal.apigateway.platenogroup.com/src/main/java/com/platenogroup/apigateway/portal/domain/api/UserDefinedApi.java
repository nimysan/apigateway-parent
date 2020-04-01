package com.platenogroup.apigateway.portal.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 用于传递给 spring cloud gateway engine去构建真正的RouteDefinition.
 * 
 * @author SeanYe
 *
 */
@Data
@ToString
@AllArgsConstructor
public class UserDefinedApi {

	private String id;

	private String upstream;

	private String accessPath;

}
