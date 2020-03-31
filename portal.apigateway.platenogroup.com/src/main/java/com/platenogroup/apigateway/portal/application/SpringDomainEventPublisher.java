package com.platenogroup.apigateway.portal.application;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.support.domainevent.AbstractDomainEvent;
import com.platenogroup.apigateway.portal.domain.support.domainevent.DomainEventLog;
import com.platenogroup.apigateway.portal.domain.support.domainevent.DomainEventLogRepository;
import com.platenogroup.apigateway.portal.infrastructure.system.SystemContext;
import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.DomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SpringDomainEventPublisher implements DomainEventPublisher, ApplicationEventPublisherAware {

	ApplicationEventPublisher springApplicationEventPublisher;

	@Autowired
	private DomainEventLogRepository domainEventLogRepository;

	@Override
	public void publish(Serializable domainEvent) {
		if (domainEvent == null) {
			return;
		}
		AbstractDomainEvent readableEvent = (AbstractDomainEvent) domainEvent;
		log.debug("Publish event {} start.", domainEvent);
		springApplicationEventPublisher.publishEvent(domainEvent);
		DomainEventLog entity = new DomainEventLog(AggregateId.generate(), SystemContext.getWorkuser(),
				readableEvent.getClass().getCanonicalName(), readableEvent.getTargetAggregateRoot().getCanonicalName(),
				readableEvent.getTargetRefId());
		domainEventLogRepository.save(entity);
		log.debug("Publish event {} done. ", domainEvent);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.springApplicationEventPublisher = applicationEventPublisher;
	}

	@PostConstruct
	public void afterSetup() {
		SystemContext.setDomainEventPublisher(this);
	}

}
