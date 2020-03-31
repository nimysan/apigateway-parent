package com.platenogroup.apigateway.portal.domain.api.event;

import com.platenogroup.apigateway.portal.domain.support.domainevent.AbstractDomainEvent;
import com.vluee.ddd.support.domain.AggregateId;

public class ApiPublishEvent extends AbstractDomainEvent {

	private static final long serialVersionUID = 3843879592534866544L;

	public ApiPublishEvent(Class<?> targetAggrerateRoot, AggregateId aggregateId) {
		super(targetAggrerateRoot, aggregateId);
	}

}
