package com.platenogroup.apigateway.portal.infrastructure.persistence.springdatajdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRepository;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRouteDefinition;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class JpaApiRepositoryTest {

	@Autowired
	private ApiRepository apiRepository;

	@Test
	public void testSaveApi() {
		Api api = sample();
		assertThat(api.getId().toSimple()).isNotNull();
		apiRepository.save(api);

		Optional<Api> findById = apiRepository.findById(api.getId());
		System.out.println(findById.get());
		assertThat(findById.get().getName()).isEqualTo("test");
	}

	@Test
	public void deleteApi() {
		Api api = sample();
		apiRepository.save(api);
		apiRepository.delete(api);
		assertThat(apiRepository.findAll()).doesNotHaveAnyElementsOfTypes(Api.class);
	}

	private Api sample() {
		Api api = new Api("test", new ApiRouteDefinition());
		return api;
	}

}
