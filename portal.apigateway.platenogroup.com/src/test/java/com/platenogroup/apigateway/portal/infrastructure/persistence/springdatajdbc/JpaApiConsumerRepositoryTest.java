package com.platenogroup.apigateway.portal.infrastructure.persistence.springdatajdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.platenogroup.apigateway.portal.domain.model.apiconsumer.ApiConsumer;
import com.platenogroup.apigateway.portal.domain.model.apiconsumer.ApiConsumerRepository;
import com.platenogroup.apigateway.portal.infrastructure.id.DistributedIdGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class JpaApiConsumerRepositoryTest {

	@Autowired
	private ApiConsumerRepository consumerRepository;

	private DistributedIdGenerator idGenerator = new DistributedIdGenerator() {
		@Override
		public String nextId() {
			return UUID.randomUUID().toString();
		}
	};

	@Test
	public void testCreateApiConsumer() {
		ApiConsumer consumer = new ApiConsumer(idGenerator.nextId());
		consumerRepository.save(consumer);
		assertThat(consumerRepository.count()).isEqualTo(1l);
	}
}
