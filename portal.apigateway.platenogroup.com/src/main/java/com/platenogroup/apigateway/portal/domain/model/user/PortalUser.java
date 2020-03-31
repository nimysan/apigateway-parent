package com.platenogroup.apigateway.portal.domain.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.platenogroup.apigateway.portal.domain.model.role.PortalUserRole;
import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.BaseAggregateRoot;

import lombok.Getter;
import lombok.Setter;

@Entity
public class PortalUser extends BaseAggregateRoot {

	@SuppressWarnings("unused")
	private PortalUser() {
		// For JPA
	}

	public PortalUser(AggregateId id, String username, String password) {
		this.aggregateId = id;
		this.username = username;
		this.password = password;
	}

	@Getter
	@Column(unique = true, nullable = false)
	private String username;

	@Getter
	@Column
	private String description;

	@Getter
	@Setter
	@Column(nullable = false)
	private String password;

	@Getter
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<PortalUserRole> roles;

	public void addRole(PortalUserRole role) {
		if (roles == null) {
			roles = new HashSet<PortalUserRole>();
		}
		roles.add(role);
	}

}
