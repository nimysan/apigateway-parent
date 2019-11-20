package com.platenogroup.apigateway.dispatcher.filter;

import java.security.Principal;
import java.util.function.Function;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class FilterConfig {

	@Bean
	public GlobalFilter customGlobalFilter() {
		return new GlobalFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				return exchange.getPrincipal().map(Principal::getName).defaultIfEmpty("Default User").map(userName -> {
					// adds header to proxied request
					exchange.getRequest().mutate().header("CUSTOM-REQUEST-HEADER", userName).build();
					return exchange;
				}).flatMap(chain::filter);
			}
		};
	}

	@Bean
	public GlobalFilter customGlobalPostFilter() {
		return new GlobalFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				return chain.filter(exchange).then(Mono.just(exchange))
						.map(new Function<ServerWebExchange, ServerWebExchange>() {
							@Override
							public ServerWebExchange apply(ServerWebExchange serverWebExchange) {
								// adds header to response
								HttpHeaders headers = serverWebExchange.getResponse().getHeaders();
								headers.set("x-png-gateway", "platenogroup");
								headers.set("x-png-gateway-version", "1.0");
								return serverWebExchange;
							}
						}).then();
			}
		};
	}
}
