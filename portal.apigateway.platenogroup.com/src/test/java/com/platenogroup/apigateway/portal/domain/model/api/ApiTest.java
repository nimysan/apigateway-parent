package com.platenogroup.apigateway.portal.domain.model.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class ApiTest {

	@Test
	public void testCargoConstruct() {
		ApiRouteDefinition route = new ApiRouteDefinition();
		Api api = new Api("test", "http", "www.baidu.com", 80, "/test");
		assertThat(api.getName()).isNotNull();
		assertThat(api.getName()).isEqualTo("test");
		assertThat(api.getRoute()).isEqualTo(route);
	}

}
