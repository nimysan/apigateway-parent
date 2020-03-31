package com.platenogroup.apigateway.portal.domain.support.domainevent;

import com.vluee.ddd.support.domain.AggregateId;

import lombok.Getter;

public abstract class AbstractDomainEvent implements java.io.Serializable {

	private static final long serialVersionUID = -8179599351528628426L;
	@Getter
	private Class<?> targetAggregateRoot;
	@Getter
	private AggregateId targetRefId;

	public AbstractDomainEvent(Class<?> targetAggregateRoot, AggregateId targetRefId) {
		super();
		this.targetAggregateRoot = targetAggregateRoot;
		this.targetRefId = targetRefId;
	}

}
