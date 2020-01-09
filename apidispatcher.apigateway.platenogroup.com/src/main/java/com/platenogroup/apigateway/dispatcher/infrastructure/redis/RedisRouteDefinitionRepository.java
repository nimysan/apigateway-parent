package com.platenogroup.apigateway.dispatcher.infrastructure.redis;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

	private static final String GATEWAY_ROUTES = "com.vluee.gateway:routes";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@PostConstruct
	public void initOne() {
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		Set<Object> keys = opsForHash.keys(GATEWAY_ROUTES);
		opsForHash.delete(GATEWAY_ROUTES, keys.toArray());
		System.out.println("List length ->" + opsForHash.keys(GATEWAY_ROUTES));
		RouteDefinition definition = new RouteDefinition();
		definition.setId("id");
		URI uri = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8888/header").build().toUri();
		// URI uri =
		// UriComponentsBuilder.fromHttpUrl("http://baidu.com").build().toUri();
		definition.setUri(uri);

		// 定义第一个断言
		PredicateDefinition predicate = new PredicateDefinition();
		predicate.setName("Path");

		Map<String, String> predicateParams = new HashMap<>(8);
		predicateParams.put("pattern", "/jd");
		predicate.setArgs(predicateParams);

		// 定义Filter
		FilterDefinition filter = new FilterDefinition();
		filter.setName("AddRequestHeader");
		Map<String, String> filterParams = new HashMap<>(8);
		// 该_genkey_前缀是固定的，见org.springframework.cloud.gateway.support.NameUtils类
		filterParams.put("_genkey_0", "header");
		filterParams.put("_genkey_1", "addHeader");
		filter.setArgs(filterParams);

		FilterDefinition filter1 = new FilterDefinition();
		filter1.setName("AddRequestParameter");
		Map<String, String> filter1Params = new HashMap<>(8);
		filter1Params.put("_genkey_0", "param");
		filter1Params.put("_genkey_1", "addParam");
		filter1.setArgs(filter1Params);

		definition.setFilters(Arrays.asList(filter, filter1));
		definition.setPredicates(Arrays.asList(predicate));

		System.out.println("definition:" + JSON.toJSONString(definition));
		opsForHash.put(GATEWAY_ROUTES, "key", JSON.toJSONString(definition));
	}

	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		List<RouteDefinition> routeDefinitions = new ArrayList<>(24);
		redisTemplate.opsForHash().values(GATEWAY_ROUTES).stream().forEach(routeDefinition -> {
			RouteDefinition parseObject = JSON.parseObject(routeDefinition.toString(), RouteDefinition.class);
			if (!parseObject.getId().contains("acb")) {
				routeDefinitions.add(parseObject);
			}

		});
		return Flux.fromIterable(routeDefinitions);
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route.flatMap(routeDefinition -> {
			redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(), JSON.toJSONString(routeDefinition));
			return Mono.empty();
		});
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		return routeId.flatMap(id -> {
			if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
				redisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
				return Mono.empty();
			}
			//系统不一定会走这个分支，如果不用defer, new NotFoundException("路由文件没有找到: " + routeId) 这个对象将会被创建，但是完全无用。
			//使用Defer以後，真正用到這個mono的時候，才會去创建一个NotFoundException对象。
			return Mono.defer(() -> Mono.error(new NotFoundException("路由文件没有找到: " + routeId)));
		});
	}

}
