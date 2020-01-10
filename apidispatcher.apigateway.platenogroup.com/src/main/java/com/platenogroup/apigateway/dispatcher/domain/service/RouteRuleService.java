package com.platenogroup.apigateway.dispatcher.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RouteRuleService {

	@Autowired
	private ApplicationEventPublisher publisher;

	public void refreshRoutes() {
		publisher.publishEvent(new RefreshRoutesEvent(RouteRuleService.this));
	}

}
