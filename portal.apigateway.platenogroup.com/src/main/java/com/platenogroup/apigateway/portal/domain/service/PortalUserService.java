package com.platenogroup.apigateway.portal.domain.service;

import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUserRole;

public interface PortalUserService {

	public PortalUser registerUserWithPassword(String userId, String password);

	public void assignRoleToUser(PortalUser user, PortalUserRole role);

	public PortalUser findByUsername(String username);

	public PortalUserRole createRole(String roleName);

}
