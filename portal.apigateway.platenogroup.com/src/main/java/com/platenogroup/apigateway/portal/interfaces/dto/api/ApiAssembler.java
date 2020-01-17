package com.platenogroup.apigateway.portal.interfaces.dto.api;

import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.model.api.Api;

@Component
public class ApiAssembler {

	public ApiRespDto assembleQueryDetail(Api api) {
		ApiRespDto dto = new ApiRespDto();
		dto.setDescription(api.getDescription());
		dto.setId(api.getId());
		dto.setName(api.getName());
		dto.setDownstreamDefinition(api.getRoute().toString());
		return dto;
	}

	public Api toEntity(ApiDto apiDto) {
		Api api = new Api(apiDto.getName(), apiDto.getDescription(), apiDto.getDestHost(), apiDto.getDestPath(),
				apiDto.getProxyMethod());
		return api;
	}

}
