package com.platenogroup.apigateway.portal.domain.model.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class ApiTest {

	@Test
	public void sameNameShouldSameApi() {
		Api api = new Api("test", "http", "www.baidu.com", 80, "/test");
		Api api2 = new Api("test1", "http", "www.google.com", 80, "/test");
		Api api3 = new Api("test", "http", "www.google.com", 80, "/test");
		assertThat(api.sameIdentityAs(api2)).isFalse();
		assertThat(api.sameIdentityAs(api3)).isTrue();
	}

	@Test
	public void testCreateApiWithoutRoute() {
		Api api = new Api("test", "http", "www.baidu.com", 80, "/test");
		assertThat(api.getName()).isNotNull();
		assertThat(api.getName()).isEqualTo("test");
	}

	@Test
	public void testApiCanOnlyBeDeactiveOnce() {
		Api api = getSampleApi();
		api.deactive();
		assertThat(api.isActive()).isFalse();
		Assertions.assertThrows(IllegalArgumentException.class, () -> api.deactive());
	}

	@Test
	public void testAddAndRemoveTagForApi() {
		Api api = getSampleApi();
		api.addTag("low");
		assertThat(api.getTags()).contains(new ApiTag("low"));

		api.removeTag("low");
		assertThat(api.getTags()).doesNotContain(new ApiTag("low"));
	}

	private Api getSampleApi() {
		Api api = new Api("test", "http", "www.baidu.com", 80, "/test");
		return api;
	}

}
