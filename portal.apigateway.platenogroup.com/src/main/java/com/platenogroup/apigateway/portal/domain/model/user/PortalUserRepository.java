package com.platenogroup.apigateway.portal.domain.model.user;

public interface PortalUserRepository {

	public void save(PortalUser user);

	public abstract PortalUser findByUsername(String username);

}
