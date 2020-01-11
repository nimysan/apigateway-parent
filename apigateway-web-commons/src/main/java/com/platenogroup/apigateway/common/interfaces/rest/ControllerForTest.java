package com.platenogroup.apigateway.common.interfaces.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.platenogroup.apigateway.common.interfaces.dto.base.PngCommonRestResponse;
import com.platenogroup.apigateway.common.interfaces.dto.base.PngRestBuilder;

@RestController
public class ControllerForTest {

	/**
	 * 请不要删除，这是用于标识内部逻辑实现存在异常的情况下的
	 */
	@GetMapping("/sample/exception")
	public final void exceptionCase() {
		throw new RuntimeException("message");
	}

	@GetMapping("/sample/argmissing")
	public final void argmissingCase(@RequestParam(required = true) String a1) {
	}

	@GetMapping("/sample/normal")
	public final PngCommonRestResponse normalReturn() {
		return PngRestBuilder.newBuilder().ok(ImmutableMap.of("a", "av"));
	}
}
