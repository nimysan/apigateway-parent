package com.platenogroup.apigateway.portal.infrastructure.repo.jpa;

import org.springframework.data.repository.CrudRepository;

import com.platenogroup.apigateway.portal.domain.role.PortalUserRole;
import com.platenogroup.apigateway.portal.domain.role.PortalUserRoleRepository;
import com.vluee.ddd.support.domain.AggregateId;

public interface PortalUserRoleRepositoryJpa
		extends PortalUserRoleRepository, CrudRepository<PortalUserRole, AggregateId> {

}
