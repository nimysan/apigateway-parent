package com.platenogroup.apigateway.portal.domain.model.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ApiTest {

	@Test
	public void testCargoConstruct() {
		ApiRouteDefinition route = new ApiRouteDefinition();
		Api api = new Api("test", route);
		assertThat(api.getId()).isNotNull();
		assertThat(api.getName()).isEqualTo("test");
		assertThat(api.getRoute()).isEqualTo(route);
	}
}
