package com.platenogroup.apigateway.dispatcher.infrastructure.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
	public static final String RABBIT_MQ_QUEUE_NAME = "platenogroup.apiroute.queue."
			+ IpHostNameUtil.getHostNameWithDefault("test");
	public static final String RABBIT_MQ_QUEUE_BEAN_NAME = "refersh_route_queue";

	@Bean(name = RABBIT_MQ_QUEUE_BEAN_NAME)
	public Queue fanoutQueue() {
		Queue queue = new Queue(RABBIT_MQ_QUEUE_NAME, true, true, true);
		return queue;
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
