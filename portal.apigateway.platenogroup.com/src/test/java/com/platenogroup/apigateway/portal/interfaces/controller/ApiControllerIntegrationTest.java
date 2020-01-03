package com.platenogroup.apigateway.portal.interfaces.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.platenogroup.apigateway.common.interfaces.dto.base.RequestDto;
import com.platenogroup.apigateway.portal.interfaces.dto.api.ApiDto;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class ApiControllerIntegrationTest {

	@Autowired
	WebTestClient testClient;

	@Test
	public void testUsage() {
		testClient.get().uri("/api/test").exchange().expectBody()
				.consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
	}

	@Test
	public void testAddApi() {
		RequestDto<ApiDto> rd = new RequestDto<ApiDto>();
		rd.setChannel("junit");
		rd.setRequestId(UUID.randomUUID().toString());
		testClient.post().uri("/api").body(Mono.just(rd), RequestDto.class).exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("123");
	}

	@Test
	public void testGetApi() {
//		testClient.get().uri("/api", ImmutableMap.of("",""));
		String apiName = "hey  I am an api";
		testClient.get().uri("/api", apiName).exchange().expectStatus().isOk();
	}

}
