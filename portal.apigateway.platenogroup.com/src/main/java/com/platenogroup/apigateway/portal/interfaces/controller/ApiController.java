package com.platenogroup.apigateway.portal.interfaces.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.common.interfaces.dto.base.IdRespBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.PngCommonRestResponse;
import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.interfaces.controller.base.BaseController;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiAssembler;
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
	public PngCommonRestResponse createApi(@RequestBody RequestDto<ApiDto> apiDto) {
		try {
			String addApi = apiService.addApi(apiAssembler.toEntity(apiDto.getBody()));
			IdRespBody addResult = new IdRespBody(addApi);
			return this.formatSuccessResponse(addResult);
		} catch (Exception e) {
			e.printStackTrace();
			return this.formatErrorResponse(e);
		}
	}

	@RequestMapping(value = "/deactive/{apiName}", method = RequestMethod.PUT)
	public PngCommonRestResponse deactive(@PathVariable String apiName) {
		try {
			apiService.deactive(apiName);
			return this.formatSuccessResponse(new IdRespBody(apiName));
		} catch (Exception e) {
			return this.formatErrorResponse(e);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public PngCommonRestResponse listAll() {
		try {
			Collection<Api> listAll = apiService.listAll();
			ResponseBody x = new ResponseBody();
			x.setBody(listAll.stream().map(api -> apiAssembler.assembleQueryDetail(api)).collect(Collectors.toList()));
			return this.formatSuccessResponse(x);
		} catch (Exception e) {
			return this.formatErrorResponse(e);
		}
	}

	@RequestMapping(value = "/{apiName}", method = RequestMethod.GET)
	public PngCommonRestResponse getByName(@PathVariable String apiName) {
		return null;
	}
}
