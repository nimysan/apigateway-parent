package com.platenogroup.apigateway.portal.domain.model.user;

import org.springframework.data.repository.CrudRepository;

public interface PortalUserRepository extends CrudRepository<PortalUser, Long> {

	public PortalUser findByUsername(String username);

}
