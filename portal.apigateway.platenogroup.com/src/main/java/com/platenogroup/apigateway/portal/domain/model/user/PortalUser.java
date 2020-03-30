package com.platenogroup.apigateway.portal.domain.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.platenogroup.apigateway.portal.domain.shared.AggregateRoot;

import lombok.Getter;
import lombok.Setter;

@AggregateRoot
@Entity
public class PortalUser {

	@SuppressWarnings("unused")
	private PortalUser() {
		// For JPA
	}

	public PortalUser(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	@Getter
	@Id
	private long id;

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
