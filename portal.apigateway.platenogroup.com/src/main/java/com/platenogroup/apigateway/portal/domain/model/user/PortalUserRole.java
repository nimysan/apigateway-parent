package com.platenogroup.apigateway.portal.domain.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor
public class PortalUserRole {

	@SuppressWarnings("unused")
	private PortalUserRole() {
		// for jpa
	}

	@Id
	private Long id;

	@Getter
	@Column
	private String roleName;
}
