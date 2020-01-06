package com.platenogroup.apigateway.portal.infrastructure.persistence.springdatajdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRepository;
import com.platenogroup.apigateway.portal.domain.model.api.SimpleTag;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class JpaApiRepositoryTest {

	/**
	 * 1. Base url ： https://api.publicapis.org/ 2. GET 3. /entries
	 */
	@Before
	public void setUp() {
		// 这是一个可以实际跑起来的API，详情参见： https://github.com/davemachado/public-api
		testPublicApi = new Api("GitHub公共API获取，用于测试", "https", "api.publicapis.org", 80, "/entries");
	}

	@Autowired
	private ApiRepository apiRepository;

	private Api testPublicApi;

	@Test
	public void testCreateAndSaveApi() {
		Api api = sample();
		apiRepository.save(api);
		Optional<Api> findById = apiRepository.findById(api.getId());
		assertThat(findById.get().getName()).isEqualTo("test");
	}

	@Test
	public void testAddTagToApi() {
		Api api = sample();
		api.addTag("test");
		apiRepository.save(api);
		Optional<Api> findById = apiRepository.findById(api.getId());
		assertThat(findById.get().getTags()).contains(new SimpleTag("test"));
	}

	@Test
	public void testDeactiveApi() {
		apiRepository.save(testPublicApi);
		Api api = apiRepository.findById(testPublicApi.getId()).get();
		api.deactive();
		apiRepository.save(testPublicApi);
		
		Optional<Api> findByName = apiRepository.findByName(testPublicApi.getName());
		Api deactiveApi = findByName.get();
		assertThat(deactiveApi.getName()).isEqualTo(testPublicApi.getName());
		assertThat(deactiveApi.isActive()).isEqualTo(false);
	}

	@Test
	public void deleteApi() {
		Api api = sample();
		apiRepository.save(api);
		apiRepository.delete(api);
		assertThat(apiRepository.findAll()).doesNotContain(api);
	}

	private Api sample() {
		Api api = new Api("test", "http", "www.baidu.com", 80, "/test");
		return api;
	}

}
