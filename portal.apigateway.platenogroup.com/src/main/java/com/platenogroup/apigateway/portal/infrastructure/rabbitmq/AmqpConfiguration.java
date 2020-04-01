package com.platenogroup.apigateway.portal.infrastructure.rabbitmq;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 取代自己配置，而是直接在管理端指定
 * 
 * @author SeanYe
 *
 */
@Configuration
public class AmqpConfiguration {

	public static final String RABBIT_MQ_EXCHANGE_NAME = "platenogroup.apiroute";

	@Bean
	FanoutExchange getFanoutExchange() {
		return new FanoutExchange(RABBIT_MQ_EXCHANGE_NAME);
	}

}
