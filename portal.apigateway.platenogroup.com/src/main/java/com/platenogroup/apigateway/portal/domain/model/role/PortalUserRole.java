package com.platenogroup.apigateway.portal.domain.model.role;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.BaseAggregateRoot;

import lombok.Getter;

@Entity
public class PortalUserRole extends BaseAggregateRoot {

	@SuppressWarnings("unused")
	private PortalUserRole() {
		// for jpa
	}

	public PortalUserRole(AggregateId id, String roleName) {
		this.aggregateId = id;
		this.roleName = roleName;
	}

	@Getter
	@Column
	private String roleName;
}
