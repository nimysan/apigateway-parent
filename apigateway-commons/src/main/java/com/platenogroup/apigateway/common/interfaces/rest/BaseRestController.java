package com.platenogroup.apigateway.common.interfaces.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseDto;

@RestController
public class BaseRestController {

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
	public final ResponseDto<Map<String, String>> normalReturn() {
		ResponseDto<Map<String, String>> rd = new ResponseDto<Map<String, String>>();
		rd.setReturnCode("00000");
		rd.setReturnMsg("");
		rd.setBody(ImmutableMap.of("a", "av"));
		return rd;
	}
}
