package com.platenogroup.apigateway.portal.infrastructure.repo.jpa;

import org.springframework.data.repository.CrudRepository;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRepository;
import com.vluee.ddd.support.domain.AggregateId;

public interface ApiRepositoryJpa extends ApiRepository, CrudRepository<Api, AggregateId> {

}
