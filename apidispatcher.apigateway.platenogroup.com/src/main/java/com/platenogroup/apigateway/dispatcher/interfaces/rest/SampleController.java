package com.platenogroup.apigateway.dispatcher.interfaces.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

import reactor.core.publisher.Mono;

/**
 * 演示如何动态添加一个API并刷新API缓存
 * @author SeanYe
 *
 */
@RestController
public class SampleController {

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/route/{name}")
	public Mono<ResponseEntity<Void>> addSample(@PathVariable String name) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(name);
		definition.setUri(URI.create("http://ip.tianqiapi.com/?ip=27.193.13.255,123.125.71.38)"));
		PredicateDefinition pd = new PredicateDefinition();
		pd.setArgs(ImmutableMap.of("pattern", "/" + name));
		pd.setName("Path");
		List<PredicateDefinition> pds = new ArrayList<>();
		pds.add(pd);
		definition.setPredicates(pds);
		return this.routeDefinitionWriter.save(Mono.just(definition))
				//存儲完成立即刷新
				.doOnSuccess(voidarg -> publisher.publishEvent(new RefreshRoutesEvent(SampleController.this)))
				.then(Mono.defer(() -> Mono.just(ResponseEntity.created(URI.create("/routes/" + name)).build())));
	}
}
