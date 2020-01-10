package com.platenogroup.apigateway.dispatcher.infrastructure.rabbitmq;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

/**
 * 使用SPEL表达式，动态使用QUEUE name
 * 
 * @author SeanYe
 *
 */
@Component
@RabbitListener(queues = "#{refersh_route_queue.name}")
public class RabbitMqMessageConsumer {

	public static final Charset CHARSET = StandardCharsets.UTF_8;

	private CountDownLatch latch = new CountDownLatch(1);

	@RabbitHandler
	public void process(String testMessage) {
		System.out.println("DirectReceiver消费者收到消息  : " + testMessage);
	}

	@RabbitHandler
	public void processByteMessage(@Payload GenericMessage<Byte[]> message) {
		Byte[] payload = message.getPayload();
		byte[] bs = new byte[payload.length];
		for (int i = 0; i < payload.length; i++) {
			bs[i] = payload[i].byteValue();
		}
		System.out.println("processByteMessage消费者收到消息  : " + new String(bs, CHARSET));
	}

	@RabbitHandler
	public void process_byteMessages(byte[] result) {
		System.out.println("process_byteMessages消费者收到消息  : " + new String(result, CHARSET));

	}

	public CountDownLatch getLatch() {
		return latch;
	}
}
