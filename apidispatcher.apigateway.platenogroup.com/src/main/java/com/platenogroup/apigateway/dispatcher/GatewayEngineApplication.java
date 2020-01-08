package com.platenogroup.apigateway.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
