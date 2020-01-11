package com.platenogroup.apigateway.portal.interfaces.dto.api;

import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.model.api.Api;

@Component
public class ApiAssembler {

	public ApiRespDto assembleQueryDetail(Api api) {
		return null;
	}

	public Api toEntity(ApiDto apiDto) {
		Api api = new Api(apiDto.getName(), apiDto.getDescription(), apiDto.getDestHost(), apiDto.getDestPath(),
				apiDto.getProxyMethod());
		return api;
	}

}
