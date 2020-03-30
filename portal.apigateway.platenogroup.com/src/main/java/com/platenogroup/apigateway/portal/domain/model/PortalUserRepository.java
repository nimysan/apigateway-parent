package com.platenogroup.apigateway.portal.domain.model;

import org.springframework.data.repository.CrudRepository;

import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;

public interface PortalUserRepository extends CrudRepository<PortalUser, Long> {

	public PortalUser findByUsername(String username);

}
