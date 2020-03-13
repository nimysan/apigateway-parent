package com.platenogroup.apigateway.portal.infrastructure.repository.inmemory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.domain.model.UserRepository;
import com.platenogroup.apigateway.portal.domain.model.user.User;

@Component
public class InMemoryUserRepository implements UserRepository {

	private Map<Long, User> userDb = new HashMap<>();

	@Override
	public void store(User user) {
		userDb.put(user.getId(), user);
	}

	public User load(Long id) {
		return userDb.get(id);
	}

}
