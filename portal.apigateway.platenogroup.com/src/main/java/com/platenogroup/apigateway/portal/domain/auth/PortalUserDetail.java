package com.platenogroup.apigateway.portal.domain.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;

public class PortalUserDetail implements UserDetails {
	private PortalUser portalUser = null;
	private List<GrantedAuthority> authorities = null;

	public PortalUserDetail(PortalUser portalUser) {
		this.portalUser = portalUser;
		authorities = new ArrayList<GrantedAuthority>();
		portalUser.getRoles().forEach(t -> {
			authorities.add(new SimpleGrantedAuthority(t.getRoleName()));
		});
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
	}

	private static final long serialVersionUID = 6056431552472407748L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableList(authorities);
	}

	@Override
	public String getPassword() {
		return portalUser.getPassword();
	}

	@Override
	public String getUsername() {
		return portalUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
