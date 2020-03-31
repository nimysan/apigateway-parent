package com.platenogroup.apigateway.portal.domain.api;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.platenogroup.apigateway.portal.domain.canonicalmodel.UserData;
import com.vluee.ddd.support.domain.BaseAggregateRoot;

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

	/**
	 * 支持的认证方式
	 */
	@OneToMany
	private List<ApiAuth> auths;

	@Column
	private int secretLevel; // 私密等級

	@Column
	private ApiStatus status = ApiStatus.ACTIVE;

	public Api(UserData createdBy, String name, String accessPath, String description) {
		this.createdBy = createdBy;
		this.name = name;
		this.accessPath = accessPath;
		this.description = description;
		// default create active api
		this.status = ApiStatus.ACTIVE;
	}

	public void publish() {
		// eventPublisher.publish(new ApiPublishEvent(getAggregateId()));
	}

	public void deactive() {
		// eventPublisher.publish(new ApiDeactiveEvent(getAggregateId()));
	}

}
