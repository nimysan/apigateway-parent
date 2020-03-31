package com.platenogroup.apigateway.portal.domain.api;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.platenogroup.apigateway.portal.domain.api.event.ApiPublishEvent;
import com.platenogroup.apigateway.portal.domain.canonicalmodel.UserData;
import com.platenogroup.apigateway.portal.infrastructure.system.SystemContext;
import com.vluee.ddd.support.domain.AggregateId;
import com.vluee.ddd.support.domain.BaseAggregateRoot;
import com.vluee.ddd.support.domain.DomainOperationException;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 聚合根
 * 
 * @author SeanYe
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Api extends BaseAggregateRoot {

	@SuppressWarnings("unused")
	private Api() {
		// for jpa
	}

	@Column(unique = true, nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	private String accessPath;

	@Transient
	private List<ApiTag> tags;

	@Embedded
	private ApiRouteDefinition routeDefinition;

	private long createdAt;

	private long lastModifiedAt;

	@Embedded
	private UserData createdBy;// 谁创建的API

	@Column
	private String description;

	@Column(nullable = false)
	private int apiVersion = 1; // 默认版本是1

	/**
	 * 支持的认证方式
	 */
	@OneToMany
	private List<ApiAuth> auths;

	@Column
	private int secretLevel; // 私密等級

	@Column
	private ApiStatus status = ApiStatus.ACTIVE;

	public Api(AggregateId id, UserData createdBy, String name, String accessPath, String description) {
		this.aggregateId = id;
		this.createdBy = createdBy;
		this.name = name;
		this.accessPath = accessPath;
		this.description = description;
		// default create active api
		this.status = ApiStatus.ACTIVE;
	}

	public void publish() {
		SystemContext.getEventPublisher().publish(new ApiPublishEvent(Api.class, getAggregateId()));
	}

	public void deactive() {
	}

	public void active() {
		if (ApiStatus.ACTIVE.equals(getStatus())) {
			throw new DomainOperationException(aggregateId, "API本身是激活的，无法再次激活");
		}
		checkRouteDefinition();
		this.status = ApiStatus.ACTIVE;
	}

	private void checkRouteDefinition() {

	}

}
