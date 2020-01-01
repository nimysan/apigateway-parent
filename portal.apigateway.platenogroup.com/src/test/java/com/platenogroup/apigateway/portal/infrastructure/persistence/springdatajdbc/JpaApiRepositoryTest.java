package com.platenogroup.apigateway.portal.infrastructure.persistence.springdatajdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class JpaApiRepositoryTest {

	/**
	 * 1. Base url ： https://api.publicapis.org/ 2. GET 3. /entries
	 */
	@Before
	public void setUp() {
		// 这是一个可以实际跑起来的API，详情参见： https://github.com/davemachado/public-api
		testPublicApis = new Api("GitHub公共API获取，用于测试", "https", "api.publicapis.org", 80, "/entries");
	}

	@Autowired
	private ApiRepository apiRepository;

	private Api testPublicApis;

	@Test
	public void testSaveApi() {
		Api api = sample();
		apiRepository.save(api);

		Optional<Api> findById = apiRepository.findById(api.getId());
		System.out.println(findById.get());
		assertThat(findById.get().getName()).isEqualTo("test");
	}

	@Test
	public void testDeactiveApi() {
		apiRepository.save(testPublicApis);
		Api api = apiRepository.findById(testPublicApis.getId()).get();
		api.deactive();
		apiRepository.save(testPublicApis);
		Optional<Api> findByName = apiRepository.findByName(testPublicApis.getName());
		Api deactiveApi = findByName.get();
		assertThat(deactiveApi.getName()).isEqualTo(testPublicApis.getName());
		assertThat(deactiveApi.isActive()).isEqualTo(false);
	}

	@Test
	public void deleteApi() {
		Api api = sample();
		apiRepository.save(api);
		apiRepository.delete(api);
		assertThat(apiRepository.findAll()).doesNotHaveAnyElementsOfTypes(Api.class);
	}

	private Api sample() {
		Api api = new Api("test", "http", "www.baidu.com", 80, "/test");
		return api;
	}

}
