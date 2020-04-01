package com.platenogroup.apigateway.portal.domain.api.event;

import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.infrastructure.domainevent.AbstractDomainEvent;

public class ApiPublishEvent extends AbstractDomainEvent {

	private static final long serialVersionUID = 3843879592534866544L;

	public ApiPublishEvent(Class<?> domainClass, AggregateId domainId) {
		super(domainClass, domainId);
	}

}
