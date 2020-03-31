package com.platenogroup.apigateway.portal.domain.model.api.events;

import java.io.Serializable;

import com.vluee.ddd.support.domain.AggregateId;

/**
 * Deactive 一个API
 * 
 * @author SeanYe
 *
 */
@SuppressWarnings("serial")
public class ApiDeactiveEvent implements Serializable {

	private final AggregateId apiId;

	public ApiDeactiveEvent(AggregateId shipmentId) {
		this.apiId = shipmentId;
	}

	public AggregateId getApiId() {
		return apiId;
	}
}
