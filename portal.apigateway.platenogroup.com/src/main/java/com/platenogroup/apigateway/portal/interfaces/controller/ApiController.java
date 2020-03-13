package com.platenogroup.apigateway.portal.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.domain.service.ApiQueryService;
import com.platenogroup.apigateway.portal.interfaces.webvo.ApiWebVo;
import com.platenogroup.apigateway.portal.interfaces.webvo.PaginatedResult;

@RestController
public class ApiController {

	@Autowired
	private ApiQueryService queryService;

	@GetMapping("/user/userId/api")
	public PaginatedResult<ApiWebVo> queryMyCreatedApis(@PathVariable String userId,
			@RequestParam(required = false, defaultValue = "1") int pageNum,
			@RequestParam(required = false, defaultValue = "25") int pageSize) {
		return queryService.queryApiByCreator(userId, pageNum, pageSize);
	}

	@GetMapping("/user/userId/api")
	public void queryMyApis(@PathVariable String userId) {

	}

}
