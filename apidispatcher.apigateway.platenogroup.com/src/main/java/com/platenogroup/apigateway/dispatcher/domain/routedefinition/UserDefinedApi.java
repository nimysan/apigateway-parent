package com.platenogroup.apigateway.dispatcher.domain.routedefinition;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDefinedApi {

	private String id;

	private String upstream;

	private String accessPath;

}
