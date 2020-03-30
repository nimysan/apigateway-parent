package com.platenogroup.apigateway.portal.domain.model.api;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.platenogroup.apigateway.portal.domain.shared.AggregateRoot;

import lombok.Data;

/**
 * 聚合根
 * 
 * @author SeanYe
 *
 */
@Data
@AggregateRoot
@Entity
public class Api {

	@Id
	private long id;

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

	@Column(nullable = false)
	private long createdBy;// 谁创建的API

	@Column
	private String description;

	/**
	 * 支持的认证方式
	 */
	@OneToMany
	private List<ApiAuth> auths;

	private int secretLevel; // 私密等級

	@Column
	private int status = ApiConstants.API_STATUS_ACTIVE; // 当前状态

	public Api(long createdBy, String name, String accessPath, String description) {
		this.createdBy = createdBy;
		this.name = name;
		this.accessPath = accessPath;
		this.description = description;
	}

}
