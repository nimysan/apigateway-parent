package com.platenogroup.apigateway.portal.application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platenogroup.apigateway.portal.domain.role.PortalUserRole;
import com.platenogroup.apigateway.portal.domain.role.PortalUserRoleRepository;
import com.platenogroup.apigateway.portal.domain.user.PortalUser;
import com.platenogroup.apigateway.portal.domain.user.PortalUserRepository;
import com.platenogroup.apigateway.portal.infrastructure.system.ApiPortalPasswordEncoder;
import com.vluee.ddd.support.domain.AggregateId;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserRoleManagementService {

	@Autowired
	private PortalUserRepository userRepository;

	@Autowired
	private PortalUserRoleRepository roleRepository;

	@Autowired
	private ApiPortalPasswordEncoder passwordEncoder;

	public PortalUser registerUserWithPassword(String username, String password) {
		PortalUser user = new PortalUser(AggregateId.generate(), username, passwordEncoder.encode(password));
		userRepository.save(user);
		return user;
	}

	public PortalUserRole createRole(String roleName) {
		PortalUserRole entity = new PortalUserRole(AggregateId.generate(), roleName);
		roleRepository.save(entity);
		return entity;
	}

	public void assignRoleToUser(PortalUser user, PortalUserRole role) {
		user.addRole(role);
		userRepository.save(user);
	}

	public PortalUser findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
