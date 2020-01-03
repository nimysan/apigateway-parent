package com.platenogroup.apigateway.portal.interfaces.dto.api;

import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseBody;

public class ApiDetailRespBody extends ResponseBody {
	private String apiCreator;

	public String getApiCreator() {
		return apiCreator;
	}

	public void setApiCreator(String apiCreator) {
		this.apiCreator = apiCreator;
	}

}
