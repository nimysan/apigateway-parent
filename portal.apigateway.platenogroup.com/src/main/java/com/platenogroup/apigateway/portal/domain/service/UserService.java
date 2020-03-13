package com.platenogroup.apigateway.portal.domain.service;

import com.platenogroup.apigateway.portal.domain.model.user.User;

public interface UserService {

	public User createUser(String id, String password);

	public void registerUserWithPassword(String userId, String password);
}
