package com.platenogroup.apigateway.portal.domain.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUserRepository;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUserRole;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUserRoleRepository;
import com.platenogroup.apigateway.portal.domain.service.PortalUserService;
import com.platenogroup.apigateway.portal.domain.shared.UniqueId;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PortalUserServiceImpl implements PortalUserService {

	@Autowired
	private PortalUserRepository userRepository;

	@Autowired
	private PortalUserRoleRepository roleRepository;

	@Autowired
	private PortalUserPasswordEncoder passwordEncoder;

	@Override
	public PortalUser registerUserWithPassword(String username, String password) {
		PortalUser user = new PortalUser(UniqueId.nextLongId(), username, passwordEncoder.encode(password));
		userRepository.save(user);
		return user;
	}

	@Override
	public PortalUserRole createRole(String roleName) {
		PortalUserRole entity = new PortalUserRole(UniqueId.nextLongId(), roleName);
		roleRepository.save(entity);
		return entity;
	}

	public void assignRoleToUser(PortalUser user, PortalUserRole role) {
		user.addRole(role);
		userRepository.save(user);
	}

	@Override
	public PortalUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
