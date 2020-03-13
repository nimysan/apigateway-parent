package com.platenogroup.apigateway.portal.domain.model.user;

import java.util.List;

import com.platenogroup.apigateway.portal.domain.shared.AggregateRoot;

import lombok.Data;

@Data
@AggregateRoot
public class User {

	private long id;

	private String userId;

	private String description;

	private String activator; // 头像

	private String email;

	private String mobilePhone;

	private String password;

	/**
	 * 创建的API列表
	 */
	private List<Long> createdApis;

	/**
	 * 允许访问的API列表
	 */
	private List<Long> accessApis;

	public User(String userId) {
		checkUnique(this.userId);
		this.userId = userId;
	}

	// TODO 应该放在哪里？
	private void checkUnique(String userId) {

	}
}
