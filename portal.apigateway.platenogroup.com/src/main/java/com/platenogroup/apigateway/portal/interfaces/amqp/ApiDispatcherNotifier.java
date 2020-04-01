package com.platenogroup.apigateway.portal.interfaces.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.api.event.ApiPublishEvent;
import com.platenogroup.apigateway.portal.infrastructure.rabbitmq.AmqpConfiguration;
import com.vluee.ddd.annotations.event.EventListener;

/**
 * 
 * 通知Spring Cloud Gateway引擎取去更新api网关定义
 * 
 * @author SeanYe
 *
 */
@Component
public class ApiDispatcherNotifier {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@EventListener
	public void handle(ApiPublishEvent event) {
		amqpTemplate.convertAndSend(AmqpConfiguration.RABBIT_MQ_EXCHANGE_NAME, "*", event.getDomainId().getId());
	}

}
