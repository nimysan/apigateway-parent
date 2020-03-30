package com.platenogroup.apigateway.portal.domain.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUserRole;

public interface PortalUserService extends UserDetailsService {

	public PortalUser registerUserWithPassword(String userId, String password);

	public void assignRoleToUser(PortalUser user, PortalUserRole role);

}
