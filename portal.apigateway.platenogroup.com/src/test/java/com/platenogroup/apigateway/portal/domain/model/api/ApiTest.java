package com.platenogroup.apigateway.portal.domain.model.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * https://www.ibm.com/developerworks/cn/java/j-introducing-junit5-part1-jupiter-api/index.html
 * 
 * @author SeanYe
 *
 */
@RunWith(JUnitPlatform.class)
@DisplayName("API模型测试")
public class ApiTest {

	@Test
	public void sameNameShouldSameApi() {
		Api api = new Api("test", "a test api", "http://www.baidu.com", "/test", "GET");
		Api api2 = new Api("test1", "a test api", "http://www.google.com", "/test", "POST");
		Api api3 = new Api("test", "a test api", "http://www.google.com", "/test", "GET");
		assertThat(api.sameIdentityAs(api2)).isFalse();
		assertThat(api.sameIdentityAs(api3)).isTrue();
	}

	@Test
	public void testCreateApiWithoutRoute() {
		Api api = new Api("test", "a test api", "http://www.baidu.com", "/test", "GET");
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
		assertThat(api.getTags()).contains(new SimpleTag("low"));

		api.removeTag("low");
		assertThat(api.getTags()).doesNotContain(new SimpleTag("low"));
	}

	private Api getSampleApi() {
		Api api = new Api("test", "a test api", "http://www.baidu.com", "/test", "GET");
		return api;
	}

}
