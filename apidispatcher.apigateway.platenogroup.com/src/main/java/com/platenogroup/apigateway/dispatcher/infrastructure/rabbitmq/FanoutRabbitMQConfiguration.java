package com.platenogroup.apigateway.dispatcher.infrastructure.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * 取代自己配置，而是直接在管理端指定
 * 
 * @author SeanYe
 *
 */
//@Configuration
public class FanoutRabbitMQConfiguration {

	public static final String RABBIT_MQ_EXCHANGE_NAME = "platenogroup.apiroute";
	public static final String RABBIT_MQ_QUEUE_NAME = "platenogroup.apiroute.queue";

	@Bean
	public Queue fanoutQueue() {
		return new Queue("TestFanoutQueue", true); // true 是否持久
	}

	@Bean
	FanoutExchange getFanoutExchange() {
		return new FanoutExchange(RABBIT_MQ_EXCHANGE_NAME);
	}

	@Bean
	Binding bindingDirect() {
		return BindingBuilder.bind(fanoutQueue()).to(getFanoutExchange());
	}
}
