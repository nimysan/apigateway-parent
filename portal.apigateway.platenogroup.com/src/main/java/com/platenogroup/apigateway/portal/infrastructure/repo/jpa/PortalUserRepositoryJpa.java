package com.platenogroup.apigateway.portal.infrastructure.repo.jpa;

import org.springframework.data.repository.CrudRepository;

import com.platenogroup.apigateway.portal.domain.user.PortalUser;
import com.platenogroup.apigateway.portal.domain.user.PortalUserRepository;
import com.vluee.ddd.support.domain.AggregateId;

public interface PortalUserRepositoryJpa extends PortalUserRepository, CrudRepository<PortalUser, AggregateId> {

}
