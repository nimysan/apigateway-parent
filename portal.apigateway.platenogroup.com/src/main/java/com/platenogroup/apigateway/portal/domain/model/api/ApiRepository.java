package com.platenogroup.apigateway.portal.domain.model.api;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * 1. 为什么Repository接口放在model里面，而不是infrastructure里面， 依据 依赖倒置原则。 2.
 * 复杂的查询通过Specification模式实现。
 * 
 * 请注意，这里强行以来了Spring-data-jdbc的CrudRepository并不是一个好的方式，只是因为选择spring-data-jdbc的原因导致的一种无奈实现。
 * 
 * @author SeanYe
 *
 */
public interface ApiRepository extends CrudRepository<Api, String> {

	Optional<Api> findByName(String id);

}
