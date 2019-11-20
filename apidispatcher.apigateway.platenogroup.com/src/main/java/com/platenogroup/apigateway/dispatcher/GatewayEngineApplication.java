package com.platenogroup.apigateway.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * MAIN: 功能文档请参照： https://cloud.spring.io/spring-cloud-gateway/reference/html/
 * 
 * Principle: https://www.reactivemanifesto.org/
 * 
 * 1. Actuator支持route的add/delete/refresh 而且是即使生效的。 可以不需要自己做了
 * 
 * @author SeanYe
 *
 */
@SpringBootApplication
public class GatewayEngineApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayEngineApplication.class, args);
	}

	// static imports from GatewayFilters and RoutePredicates
	@Bean
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
