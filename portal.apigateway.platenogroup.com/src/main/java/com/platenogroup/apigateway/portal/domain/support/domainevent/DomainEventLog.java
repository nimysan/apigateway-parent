package com.platenogroup.apigateway.portal.domain.support.domainevent;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.springframework.data.annotation.CreatedDate;

import com.platenogroup.apigateway.portal.domain.canonicalmodel.UserData;
import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.BaseAggregateRoot;

@Entity
public class DomainEventLog extends BaseAggregateRoot {

	public enum DomainEventStatus {

	}

	@SuppressWarnings("unused")
	private DomainEventLog() {
		// for jpa
	}

	public DomainEventLog(AggregateId id, UserData createdBy, String eventName, String aggregateRootName,
			AggregateId refAggregateId) {
		this.aggregateId = id;
		this.eventName = eventName;
		this.aggregateRootName = aggregateRootName;
		this.refAggregateId = refAggregateId;
		this.createdBy = createdBy;
	}

	@Column(nullable = false)
	private String eventName;

	@CreatedDate
	private Date createdAt;

	@Embedded
	private UserData createdBy;

	@Column(nullable = false)
	private String aggregateRootName;

	@AttributeOverrides({
			@AttributeOverride(name = "aggregateId", column = @Column(name = "ref_id", nullable = false)) })
	private AggregateId refAggregateId;

}
