package com.platenogroup.apigateway.portal.infrastructure.repo.jpa;

import org.springframework.data.repository.CrudRepository;

import com.platenogroup.apigateway.portal.domain.support.domainevent.DomainEventLog;
import com.platenogroup.apigateway.portal.domain.support.domainevent.DomainEventLogRepository;
import com.vluee.ddd.support.domain.AggregateId;

public interface DomainEventLogRepositoryJpa
		extends DomainEventLogRepository, CrudRepository<DomainEventLog, AggregateId> {
}
