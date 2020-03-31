package com.platenogroup.apigateway.portal.domain.model.api;

public enum ApiStatus {

	ACTIVE(1), DEACTIVE(2), SOFT_REMOVED(3);

	private int status;

	ApiStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}

}
