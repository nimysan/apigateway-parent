package com.platenogroup.apigateway.portal.domain.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.portal.domain.model.UserRepository;
import com.platenogroup.apigateway.portal.domain.model.user.User;
import com.platenogroup.apigateway.portal.domain.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(String userId, String password) {
		User user = new User(userId);
		user.setPassword(password);
		userRepository.store(user);
		fireEvent(new UserCreatedDomainEvent());
		return user;
	}

	private void fireEvent(UserCreatedDomainEvent userCreatedDomainEvent) {

	}

	@Override
	public void registerUserWithPassword(String userId, String password) {

	}

}
