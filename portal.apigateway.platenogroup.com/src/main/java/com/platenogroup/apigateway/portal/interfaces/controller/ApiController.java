package com.platenogroup.apigateway.portal.interfaces.controller;

import javax.validation.Valid;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.common.interfaces.dto.base.IdRespBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseDto;
import com.platenogroup.apigateway.portal.application.assembler.ApiAssembler;
import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.interfaces.controller.base.BaseController;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDetailRespBody;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

	@Autowired
	public ApiService apiService;

	@Autowired
	public ApiAssembler apiAssembler;

	@RequestMapping("/test")
	public String simpleTest() {
		return RandomUtils.JVM_RANDOM.toString();
	}

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
	public ResponseDto createApi(@RequestBody @Valid RequestDto<ApiDto> apiDto) {
		return this.formatSuccessResponse(new IdRespBody("123"));
	}

	@RequestMapping(value = "/{apiName}", method = RequestMethod.GET)
	public ResponseDto getByName(@PathVariable String apiName) {
		ApiDetailRespBody byName = apiService.getByName(apiName);
		return this.formatSuccessResponse(byName);
	}
}
