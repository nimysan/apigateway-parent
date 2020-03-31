package com.platenogroup.apigateway.portal.interfaces.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.domain.service.ApiService;
import com.platenogroup.apigateway.portal.interfaces.dto.ApiDto;

@RestController
@RequestMapping("/api")
public class ApiController {

	// @Autowired
	// private ApiQueryService queryService;

	@Autowired
	private ApiService apiService;

	@GetMapping
	public List<ApiDto> list() {
		return Collections.emptyList();
	}

	/**
	 * ROLE_前缀必须带 请查看 RoleVoter
	 */
	@GetMapping("/sample")
	@PreAuthorize("hasAuthority('ROLE_api_creator')")
	public void createSampleApi() {
		apiService.addApi("business", "a.b.c", "tetst");
	}

//	@PreAuthorize("hasRole('api_creator')")
//	public ApiDto create() {
//
//	}

}
