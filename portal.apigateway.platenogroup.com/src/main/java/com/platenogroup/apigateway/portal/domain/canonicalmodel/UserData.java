/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.platenogroup.apigateway.portal.domain.canonicalmodel;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.vluee.ddd.annotations.domain.ValueObject;
import com.vluee.ddd.support.domain.AggregateId;

/**
 * Client's snapshot
 * 
 * @author Slawek
 */
@ValueObject
@Embeddable
public class UserData {

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "aggregateId", column = @Column(name = "user_id", nullable = false)) })
	private AggregateId aggregateId;

	@Column(name = "user_name")
	private String name;

	@SuppressWarnings("unused")
	private UserData() {
	}

	public UserData(AggregateId aggregateId, String name) {
		this.aggregateId = aggregateId;
		this.name = name;
	}

	public AggregateId getAggregateId() {
		return aggregateId;
	}

	public String getName() {
		return name;
	}

}
