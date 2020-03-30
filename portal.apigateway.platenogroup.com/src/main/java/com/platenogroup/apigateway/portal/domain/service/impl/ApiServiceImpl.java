package com.platenogroup.apigateway.portal.domain.service.impl;

import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.portal.domain.service.ApiService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiServiceImpl implements ApiService {

	@Override
	public void addApi(String name, String accessPath) {
		log.debug("Create api with name {} and path:{}", name, accessPath);
	}
}
