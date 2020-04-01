package com.platenogroup.apigateway.dispatcher.domain.routedefinition;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RouteDefinitionAssembler {

	public RouteDefinition assemble(String jsonText) {
		// TODO 从api gateway portal的，翻译为spring cloud gateway的route definition
		UserDefinedApi userDefinedApi = JSON.parseObject(jsonText, UserDefinedApi.class);

		RouteDefinition definition = new RouteDefinition();
		definition.setId(userDefinedApi.getId());
		URI uri = UriComponentsBuilder.fromHttpUrl(userDefinedApi.getUpstream()).build().toUri();
		definition.setUri(uri);

		// 定义第一个断言
		PredicateDefinition predicate = new PredicateDefinition();
		predicate.setName("Path");

		Map<String, String> predicateParams = new HashMap<>(8);
		predicateParams.put("pattern", userDefinedApi.getAccessPath());
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

		log.debug("Route Definition: {} ", JSON.toJSONString(definition, true));

		return definition;
	}
}
