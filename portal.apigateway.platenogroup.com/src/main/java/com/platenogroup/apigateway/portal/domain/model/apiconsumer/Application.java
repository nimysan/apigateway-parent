package com.platenogroup.apigateway.portal.domain.model.apiconsumer;

import javax.persistence.Column;
import javax.persistence.Id;

import com.platenogroup.apigateway.portal.domain.shared.Entity;

@javax.persistence.Entity
public class Application implements Entity<Application> {

	@Id
	private String applicationId;

	/**
	 * 一个名称
	 */
	private String name;

	/**
	 * 详细说明
	 */
	private String description;

	@Column(name = "isActive")
	private boolean isActive;

	@Override
	public boolean sameIdentityAs(Application other) {
		return false;
	}

	public void deactive() {
		this.isActive = false;
	}

	public void active() {
		this.isActive = true;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
