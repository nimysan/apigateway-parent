package com.platenogroup.apigateway.dispatcher.interfaces.amqp;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.dispatcher.domain.routedefinition.RouteDefinitionService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author SeanYe
 *
 */
@Component
@RabbitListener(queues = "#{refersh_route_queue.name}") // 使用SPEL表达式，动态使用QUEUE name
@Slf4j
public class RouteDefinitionReceiver {

	public static final Charset CHARSET = StandardCharsets.UTF_8;

	@Autowired
	private RouteDefinitionService routeDefinitionService;

	@RabbitHandler
	public void process(String routeDefinitionJsonString) {
		log.debug("---->Route Definition from portal is {} ", routeDefinitionJsonString);
		routeDefinitionService
				.saveOrUpdateDefinition(routeDefinitionService.createRouteDefinition(routeDefinitionJsonString), true);
	}
}
