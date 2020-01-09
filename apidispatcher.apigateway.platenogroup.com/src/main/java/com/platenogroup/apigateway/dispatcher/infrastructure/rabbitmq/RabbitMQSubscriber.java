package com.platenogroup.apigateway.dispatcher.infrastructure.rabbitmq;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = FanoutRabbitMQConfiguration.RABBIT_MQ_QUEUE_NAME) // 监听的队列名称 TestDirectQueue
public class RabbitMQSubscriber {
	private CountDownLatch latch = new CountDownLatch(1);

	@RabbitHandler
	public void process(Message testMessage) {
		System.out.println("DirectReceiver消费者收到消息  : " + testMessage);
	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
