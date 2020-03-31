package com.platenogroup.apigateway.portal.interfaces.springsecurity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.application.service.UserRoleManagementService;
import com.platenogroup.apigateway.portal.domain.user.PortalUser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PortalUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRoleManagementService portalUserService;

	@Autowired
	private PortalUserAssembler userAssembler;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PortalUser portalUser = portalUserService.findByUsername(username);
		if (portalUser == null) {
			log.warn("No user found with username {}", username);
			throw new UsernameNotFoundException(String.format("No user found with username."));
		}
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		GrantedAuthority au = new SimpleGrantedAuthority("ROLE_USER");
		list.add(au);
		JwtUserDetail JwtUser = userAssembler.assembleUserDetail(portalUser);
		new JwtUserDetail(portalUser.getAggregateId().getId(), username, "e10adc3949ba59abbe56e057f20f883e", list);
		log.debug("User info: {}", JwtUser);
		return JwtUser;
	}

}
