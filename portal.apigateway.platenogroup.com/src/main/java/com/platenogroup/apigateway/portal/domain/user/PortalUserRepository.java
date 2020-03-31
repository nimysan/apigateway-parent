package com.platenogroup.apigateway.portal.domain.user;

public interface PortalUserRepository {

	public void save(PortalUser user);

	public PortalUser findByUsername(String username);

}
