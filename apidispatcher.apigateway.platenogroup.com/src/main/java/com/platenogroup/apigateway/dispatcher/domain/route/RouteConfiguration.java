package com.platenogroup.apigateway.dispatcher.domain.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Some helpful information.
 * 
 * https://cloud.spring.io/spring-cloud-gateway/reference/html/#creating-and-deleting-a-particular-route
 * 
 * @author SeanYe
 *
 */
@Configuration
public class RouteConfiguration {

	// static imports from GatewayFilters and RoutePredicates
	// @Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		//@formatter:off
	    return builder.routes()
	            .route(r -> r.host("**.abc.org").and().path("/image/png")
	                .filters(f ->
	                        f.addResponseHeader("X-TestHeader", "foobar"))
	                .uri("http://httpbin.org:80")
	            )
	            .route(r -> r.path("/image/webp")
	                .filters(f ->
	                        f.addResponseHeader("X-AnotherHeader", "baz"))
	                .uri("http://httpbin.org:80")
	            )
	            /*
	            .route(r -> r.order(-1)
	                .host("**.throttle.org").and().path("/get")
	                .filters(f -> f.filter(throttle.apply(1,
	                        1,
	                        10,
	                        TimeUnit.SECONDS)))
	                .uri("http://httpbin.org:80")
	            )*/
	            .build();
	 //@formatter:on
	}
}
