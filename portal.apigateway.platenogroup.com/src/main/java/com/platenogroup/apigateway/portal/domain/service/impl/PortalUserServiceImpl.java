package com.platenogroup.apigateway.portal.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.platenogroup.apigateway.portal.domain.auth.PortalUserAssembler;
import com.platenogroup.apigateway.portal.domain.model.PortalUserRepository;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUser;
import com.platenogroup.apigateway.portal.domain.model.user.PortalUserRole;
import com.platenogroup.apigateway.portal.domain.service.PortalUserService;
import com.platenogroup.apigateway.portal.domain.shared.UniqueId;
import com.platenogroup.apigateway.portal.interfaces.springsecurity.JwtUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PortalUserServiceImpl implements PortalUserService {

	@Autowired
	private PortalUserRepository userRepository;

	// TODO 此处不应该有此依赖
	@Autowired
	private PortalUserAssembler portalUserAssembler;

	@Override
	public PortalUser registerUserWithPassword(String username, String password) {
		PortalUser user = new PortalUser(UniqueId.nextLongId(), username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	public void assignRoleToUser(PortalUser user, PortalUserRole role) {
		user.addRole(role);
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PortalUser portalUser = userRepository.findByUsername(username);
//		UserDetails assembleUserDetail = portalUserAssembler.assembleUserDetail(portalUser);
//		log.debug("Portal user is: {}", JSON.toJSONString(assembleUserDetail));

		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		GrantedAuthority au = new SimpleGrantedAuthority("ROLE_USER");
		list.add(au);
		// 123456 自定义MD5加密后=e10adc3949ba59abbe56e057f20f883e
		JwtUser JwtUser = new JwtUser(username, "e10adc3949ba59abbe56e057f20f883e", list);
		if (JwtUser == null) {
			throw new UsernameNotFoundException(String.format("No user found with username."));
		}
		System.out.println(JwtUser);
		return JwtUser;
	}

}
