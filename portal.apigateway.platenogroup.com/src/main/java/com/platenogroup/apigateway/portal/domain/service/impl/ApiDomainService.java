package com.platenogroup.apigateway.portal.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.portal.domain.api.Api;
import com.platenogroup.apigateway.portal.domain.api.ApiFactory;
import com.platenogroup.apigateway.portal.domain.api.ApiRepository;
import com.platenogroup.apigateway.portal.domain.service.ApiService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiDomainService implements ApiService {

	@Autowired
	private ApiFactory apiFactory;

	@Autowired
	private ApiRepository apiRepository;

	@Override
	public void addApi(String name, String accessPath, String description) {
		Api api = apiFactory.createApi(name, accessPath, description);
		apiRepository.save(api);
		log.debug("Create api with name {} and path:{}", name, accessPath);
	}
}
