package com.platenogroup.apigateway.portal.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.common.interfaces.dto.base.IdRespBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseDto;
import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.interfaces.controller.base.BaseController;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiAssembler;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDetailRespBody;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiRespBody;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

	@Autowired
	public ApiService apiService;

	@Autowired
	public ApiAssembler apiAssembler;

	@RequestMapping("/copy")
	public String copy() {
		return "copy";
	}

	/**
	 * 创建一个API并返回API唯一ID
	 * 
	 * @param requestDto
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseDto createApi(@RequestBody RequestDto<ApiDto> apiDto) {
		try {
			String addApi = apiService.addApi(apiAssembler.toEntity(apiDto.getBody()));
			IdRespBody addResult = new IdRespBody(addApi);
			return this.formatSuccessResponse(addResult);
		} catch (Exception e) {
			return this.formatErrorResponse(e);
		}
	}

	@RequestMapping(value = "/deactive/{apiName}", method = RequestMethod.PUT)
	public ResponseDto deactive(@PathVariable String apiName) {
		try {
			apiService.deactive(apiName);
			return this.formatSuccessResponse(new IdRespBody(apiName));
		} catch (Exception e) {
			return this.formatErrorResponse(e);
		}
	}

	@RequestMapping(value = "/{apiName}", method = RequestMethod.GET)
	public ResponseDto getByName(@PathVariable String apiName) {
		return null;
	}
}
