package com.platenogroup.apigateway.dispatcher.domain.routedefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RouteDefinitionService {

	@Autowired
	private RouteDefinitionAssembler assembler;

	@Autowired
	private RouteDefinitionRepository routeDefinitionRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	public RouteDefinition createRouteDefinition(String jsonText) {
		try {
			return assembler.assemble(jsonText);
		} catch (Exception e) {
			log.error("Failed to convert jsonText:  {}  ", e);
			return null;
		}
	}

	public void saveOrUpdateDefinition(RouteDefinition definition, boolean refresh) {
		if (definition == null) {
			return;
		}
		routeDefinitionRepository.save(Mono.just(definition)).block();
		// TODO is this a great way?
		if (refresh) {
			publisher.publishEvent(new RefreshRoutesEvent(RouteDefinitionService.this));
		}
	}

}
