package com.platenogroup.apigateway.portal.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platenogroup.apigateway.portal.domain.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public void register(@RequestParam String userId, @RequestParam String password) {
		userService.registerUserWithPassword(userId, password);
	}

	public void userLogin(@RequestParam String userId, @RequestParam String password) {

	}

}
