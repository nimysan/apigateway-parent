package com.platenogroup.apigateway.portal.domain.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;

@Component
public class PortalUserAssembler {

	public UserDetails assembleUserDetail(PortalUser portalUser) {
		return new PortalUserDetail(portalUser);
	}

}
