package com.platenogroup.apigateway.dispatcher.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@RequestMapping(method = RequestMethod.GET, value = "/sample")
	public String sample() {
		return UUID.randomUUID().toString();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sample/{expected}")
	public String sampleWithPathVariable(@PathVariable String expected) {
		return expected;
	}
}
