package com.platenogroup.apigateway.portal.domain.model.api;

import java.util.List;

import javax.validation.constraints.NotNull;

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
public class Api {

	private long id;

	private String name;

	private String accessPath;

	private List<ApiTag> tags;

	private ApiRouteDefinition routeDefinition;

	private long createdAt;

	private long lastModifiedAt;

	private long createdBy;// 谁创建的API

	private String description;

	/**
	 * 支持的认证方式
	 */
	private List<ApiAuth> auths;

	private int secretLevel; // 私密等級

	private int status = ApiConstants.API_STATUS_ACTIVE; // 当前状态

	public Api(long createdBy, String name, String accessPath, String description) {

		this.createdBy = createdBy;
		this.name = name;
		this.accessPath = accessPath;
		this.description = description;

	}

}
