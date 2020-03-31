package com.platenogroup.apigateway.portal.interfaces.springsecurity;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.user.PortalUser;

@Component
public class PortalUserAssembler {

	public JwtUserDetail assembleUserDetail(PortalUser portalUser) {
		return new JwtUserDetail(portalUser.getAggregateId().getId(), portalUser.getUsername(),
				portalUser.getPassword(), portalUser.getRoles().stream()
						.map(t -> new SimpleGrantedAuthority("ROLE_" + t.getRoleName())).collect(Collectors.toList()));
	}

}
