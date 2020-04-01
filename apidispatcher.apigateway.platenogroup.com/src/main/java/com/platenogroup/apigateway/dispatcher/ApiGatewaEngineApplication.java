package com.platenogroup.apigateway.dispatcher;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.platenogroup.apigateway.dispatcher.infrastructure.rabbitmq.IpHostNameUtil;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class ApiGatewaEngineApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewaEngineApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.debug("网关引擎初始化， 网关所在机器   {} ", IpHostNameUtil.getIpWithDefault("---nonip---"));
		// Initialize
	}

}
