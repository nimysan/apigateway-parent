package com.platenogroup.apigateway.portal.application.service;

import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.common.interfaces.dto.base.IdRespBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDetailRespBody;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;
import com.platenogroup.apigateway.portal.interfaces.dto.api.DeleteApiRespBody;

@Service
public class ApiServiceImpl implements ApiService {

	@Override
	public ApiDetailRespBody getByName(String apiName) {
		return null;
	}

	@Override
	public IdRespBody addApi(RequestDto<ApiDto> apiDto) {
		return null;
	}

	@Override
	public DeleteApiRespBody deleteApi(String apiId) {
		return null;
	}

}
