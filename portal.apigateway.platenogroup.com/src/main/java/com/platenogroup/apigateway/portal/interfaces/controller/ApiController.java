package com.platenogroup.apigateway.portal.interfaces.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.common.interfaces.dto.base.PngCommonRestResponse;
import com.platenogroup.apigateway.common.interfaces.dto.base.PngRestBuilder;
import com.platenogroup.apigateway.common.interfaces.rest.BaseRestController;
import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiAssembler;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseRestController {

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
	public PngCommonRestResponse createApi(@RequestBody ApiDto apiDto) throws Exception {
		String addApi = apiService.addApi(apiAssembler.toEntity(apiDto));
		return PngRestBuilder.newBuilder().ok(addApi);
	}

	@RequestMapping(value = "/deactive/{apiName}", method = RequestMethod.PUT)
	public PngCommonRestResponse deactive(@PathVariable String apiName) {
		return PngRestBuilder.newBuilder().okAsEmpty();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public PngCommonRestResponse listAll() throws Exception {
		return PngRestBuilder.newBuilder().ok(apiService.listAll().stream()
				.map(api -> apiAssembler.assembleQueryDetail(api)).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/{apiName}", method = RequestMethod.GET)
	public PngCommonRestResponse getByName(@PathVariable String apiName) {
		return null;
	}
}
