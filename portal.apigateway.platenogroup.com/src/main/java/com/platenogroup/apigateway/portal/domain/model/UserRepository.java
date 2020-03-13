package com.platenogroup.apigateway.portal.domain.model;

import com.platenogroup.apigateway.portal.domain.model.user.User;

public interface UserRepository {

	void store(User user);

}
