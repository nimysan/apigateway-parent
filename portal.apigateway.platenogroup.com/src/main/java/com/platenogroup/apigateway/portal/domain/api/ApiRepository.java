package com.platenogroup.apigateway.portal.domain.api;

import java.util.Optional;

import com.vluee.ddd.support.domain.AggregateId;

public interface ApiRepository {

	public void save(Api api);

	public Optional<Api> findById(AggregateId id);

	public Iterable<Api> findAll();

}
