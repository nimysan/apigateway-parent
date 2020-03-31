package com.platenogroup.apigateway.portal.domain.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.platenogroup.apigateway.portal.domain.api.Api;
import com.platenogroup.apigateway.portal.domain.api.ApiFactory;
import com.platenogroup.apigateway.portal.domain.api.ApiRepository;
import com.platenogroup.apigateway.portal.domain.service.ApiService;
import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.DomainOperationException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ApiDomainService implements ApiService {

	@Autowired
	private ApiFactory apiFactory;

	@Autowired
	private ApiRepository apiRepository;

	@Override
	public AggregateId createApi(String name, String accessPath, String description) {
		Api api = apiFactory.createApi(name, accessPath, description);
		// TODO check name and accessPath should be unique
		apiRepository.save(api);
		log.debug("Create api with name {} and path:{}", name, accessPath);
		return api.getAggregateId();
	}

	@Override
	public void active(AggregateId id) {
		Api api = apiRepository.findById(id).orElseThrow(() -> new DomainOperationException(id, "目标对象不存在"));
		api.active();
		apiRepository.save(api);
	}

	@Override
	public List<Api> findAll() {
		return Lists.newArrayList(apiRepository.findAll());
	}

	@Override
	public void publish(String resourceId) {
		AggregateId id = new AggregateId(resourceId);
		Api api = apiRepository.findById(id)
				.orElseThrow(() -> new DomainOperationException(id, String.format("目标对象%s不存在", id)));
		api.publish();
		log.debug("Publish resource {} successed", resourceId);
		// TODO publish to redis and wait "api gateway engine" to consume it
	}
}
