package com.platenogroup.apigateway.dispatcher.interfaces.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.filter.factory.SetPathGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 演示如何动态添加一个API并刷新API缓存
 * 
 * @author SeanYe
 *
 */
@RestController
public class SampleController {

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private RouteDefinitionLocator  routeDefinitionLocator;

	@Autowired
	private RouteLocatorBuilder builder;

	@GetMapping("/route/{name}")
	public Mono<ResponseEntity<Void>> addSample(@PathVariable String name) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(name);
		definition.setUri(URI.create("https://api.coindesk.com"));
		PredicateDefinition pd = new PredicateDefinition();
		pd.setArgs(ImmutableMap.of("pattern", "/" + name));
		pd.setName("Path");
		List<PredicateDefinition> pds = new ArrayList<>();
		pds.add(pd);
		definition.setPredicates(pds);
		
//		Flux<Route> take = builder.routes().route(name, t -> t.path("/" + name)
//				.filters(f -> f.setPath("/v1/bpi/currentprice/CNY.json")).uri("https://api.coindesk.com")).build();

		FilterDefinition fd = new FilterDefinition();
		fd.setName("StripPrefix");
		fd.setArgs(ImmutableMap.of("parts", "1"));

		FilterDefinition spFd = new FilterDefinition();
		spFd.setName("SetPath");
		spFd.setArgs(ImmutableMap.of("template", "/v1/bpi/currentprice/CNY.json"));

		definition.setFilters(ImmutableList.of(fd, spFd));

		return this.routeDefinitionWriter.save(Mono.just(definition))
				// 存儲完成立即刷新
				.doOnSuccess(voidarg -> publisher.publishEvent(new RefreshRoutesEvent(SampleController.this)))
				.then(Mono.defer(() -> Mono.just(ResponseEntity.created(URI.create("/routes/" + name)).build())));
	}
}
