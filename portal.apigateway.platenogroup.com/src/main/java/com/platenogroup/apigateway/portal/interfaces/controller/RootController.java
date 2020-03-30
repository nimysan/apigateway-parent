package com.platenogroup.apigateway.portal.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.platenogroup.apigateway.portal.interfaces.webvo.SampleVo;

@RestController
public class RootController {

	@GetMapping("/sample")
	public SampleVo getSample() {
		return new SampleVo("joey", "a new man");
	}
}
