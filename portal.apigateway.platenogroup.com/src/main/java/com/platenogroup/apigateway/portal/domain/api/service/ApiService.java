package com.platenogroup.apigateway.portal.domain.api.service;

import java.util.List;

import com.platenogroup.apigateway.portal.domain.api.Api;
import com.platenogroup.apigateway.portal.interfaces.dto.RouteDefintionDto;
import com.vluee.ddd.support.domain.AggregateId;

public interface ApiService {

	AggregateId createApi(String name, String accessPath, String description);

	void active(AggregateId id);

	List<Api> findAll();

	void publish(String resourceId);

	void setRouteDefinition(AggregateId id, RouteDefintionDto definition);

}
