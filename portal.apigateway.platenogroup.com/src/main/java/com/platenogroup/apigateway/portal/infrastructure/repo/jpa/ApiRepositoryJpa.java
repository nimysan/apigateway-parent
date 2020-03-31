package com.platenogroup.apigateway.portal.infrastructure.repo.jpa;

import com.platenogroup.apigateway.portal.domain.model.api.Api;
import com.platenogroup.apigateway.portal.domain.model.api.ApiRepository;
import com.vluee.ddd.annotations.domain.DomainRepositoryImpl;
import com.vluee.ddd.support.infrastructure.repository.jpa.GenericJpaRepository;

@DomainRepositoryImpl
public class ApiRepositoryJpa extends GenericJpaRepository<Api> implements ApiRepository {

}
