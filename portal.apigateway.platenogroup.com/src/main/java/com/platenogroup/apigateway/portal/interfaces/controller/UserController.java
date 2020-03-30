package com.platenogroup.apigateway.portal.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.domain.service.PortalUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	@Autowired
	private PortalUserService userService;

	@PostMapping("/login")
	public void login(@RequestParam String username, @RequestParam String password) {
		log.debug("here {}, {} ", username, password);
	}

	@PostMapping("/user")
	public void register(@RequestParam String userId, @RequestParam String password) {
		userService.registerUserWithPassword(userId, password);
	}
}
