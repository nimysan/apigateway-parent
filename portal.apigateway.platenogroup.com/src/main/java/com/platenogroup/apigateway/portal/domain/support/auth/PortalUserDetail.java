package com.platenogroup.apigateway.portal.domain.support.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.platenogroup.apigateway.portal.domain.user.PortalUser;
import com.vluee.ddd.support.domain.AggregateId;

public class PortalUserDetail implements UserDetails {

	private static final long serialVersionUID = 6056431552472407748L;

	private List<GrantedAuthority> authorities = null;

	private AggregateId aggregateId;
	private String username;
	private String password;

	public PortalUserDetail(PortalUser portalUser) {
		authorities = new ArrayList<GrantedAuthority>();
		portalUser.getRoles().forEach(t -> {
			authorities.add(new SimpleGrantedAuthority(t.getRoleName()));
		});
		authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		this.aggregateId = portalUser.getAggregateId();
		this.username = portalUser.getUsername();
		this.password = portalUser.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableList(authorities);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	/**
	 * @return the aggregateId
	 */
	public AggregateId getAggregateId() {
		return aggregateId;
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
