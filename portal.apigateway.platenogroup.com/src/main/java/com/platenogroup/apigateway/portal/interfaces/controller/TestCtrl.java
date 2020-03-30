package com.platenogroup.apigateway.portal.interfaces.controller;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import com.platenogroup.apigateway.portal.interfaces.springsecurity.JwtUserDetail;
import com.platenogroup.apigateway.portal.interfaces.springsecurity.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/test")
@Slf4j
public class TestCtrl {

	@GetMapping("aaa")
	public String aaa() {
		// 获取当前登录用户
		JwtUserDetail jwtUser = JwtUserDetail.currentUser();
		System.out.println(jwtUser);
		System.getProperties().list(System.out);
		return "sucess.........";
	}

	@PostMapping("parseJWT")
	public String login(String jwt) {
		Claims Claims = JwtUtils.parseJWT(jwt);
		return Claims.getId();
	}

	@RequestMapping("/error")
	public void error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			response.getWriter().write("{\"result\":\"error\"}");
			printWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
