package com.platenogroup.apigateway.portal.domain.model.api.events;

import java.io.Serializable;

import com.vluee.ddd.support.domain.AggregateId;

/**
 * 发布一个API
 * @author SeanYe
 *
 */
@SuppressWarnings("serial")
public class ApiPublishEvent implements Serializable {

	private final AggregateId apiId;

	public ApiPublishEvent(AggregateId shipmentId) {
		this.apiId = shipmentId;
	}

	public AggregateId getApiId() {
		return apiId;
	}
}
