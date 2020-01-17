package com.platenogroup.apigateway.portal.interfaces.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.application.service.ApiService;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiAssembler;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;
import com.vluee.codeflower.rest.GeneralRestResponse;
import com.vluee.codeflower.rest.GeneralRestResponseBuilder;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	public ApiService apiService;

	@Autowired
	public ApiAssembler apiAssembler;

	@Autowired
	public GeneralRestResponseBuilder responseBuilder;

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
	public GeneralRestResponse<String> createApi(@RequestBody ApiDto apiDto) throws Exception {
		String addApi = apiService.addApi(apiAssembler.toEntity(apiDto));
		return responseBuilder.okWithEntity(addApi);
	}

	@RequestMapping(value = "/deactive/{apiName}", method = RequestMethod.PUT)
	public GeneralRestResponse<Void> deactive(@PathVariable String apiName) {
		apiService.deactive(apiName);
		return responseBuilder.ok();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public GeneralRestResponse listAll() throws Exception {
		return responseBuilder.okWithEntity(apiService.listAll().stream()
				.map(api -> apiAssembler.assembleQueryDetail(api)).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/{apiName}", method = RequestMethod.GET)
	public GeneralRestResponse getByName(@PathVariable String apiName) throws Exception {
		return responseBuilder.okWithEntity(apiAssembler.assembleQueryDetail(apiService.getByName(apiName).get()));
	}
}
