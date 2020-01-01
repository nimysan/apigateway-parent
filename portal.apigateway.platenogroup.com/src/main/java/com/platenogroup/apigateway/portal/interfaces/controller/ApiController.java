package com.platenogroup.apigateway.portal.interfaces.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.interfaces.controller.base.BaseController;
import com.platenogroup.apigateway.portal.interfaces.dto.ApiDto;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {

	@RequestMapping("/test")
	public String simpleTest() {
		return RandomUtils.JVM_RANDOM.toString();
	}

	public void createApi(ApiDto apiDto) {

	}
}
