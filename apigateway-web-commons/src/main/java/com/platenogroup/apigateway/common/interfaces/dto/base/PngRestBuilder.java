package com.platenogroup.apigateway.common.interfaces.dto.base;

import org.springframework.util.StringUtils;

import com.google.common.collect.ImmutableMap;
import com.platenogroup.apigateway.common.constants.ReturnCode;

/**
 * Rest返回构建器
 * 
 * @author SeanYe
 *
 * @param <T>
 */
public class PngRestBuilder {

	private static final Object EMPTY_BODY = ImmutableMap.of();
	private static final String EMPTY_MESSAGE = "";

	public static final PngRestBuilder newBuilder() {
		return new PngRestBuilder();
	}

	private PngRestBuilder() {
	}

	public PngCommonRestResponse ok(Object body) {
		return new PngCommonRestResponse(ReturnCode.SUCCESS, EMPTY_MESSAGE, body);
	}

	public PngCommonRestResponse okAsEmpty() {
		return new PngCommonRestResponse(ReturnCode.SUCCESS, EMPTY_MESSAGE, EMPTY_BODY);
	}

	public PngCommonRestResponse failWithCode(String code) {
		return new PngCommonRestResponse(code, getCodeDescription(code), EMPTY_BODY);
	}

	public PngCommonRestResponse failWithoutCode(String message) {
		String code = ReturnCode.UNKNOWN_ERROR;
		return new PngCommonRestResponse(code, StringUtils.isEmpty(message) ? getCodeDescription(code) : message,
				EMPTY_BODY);
	}

	private String getCodeDescription(String code) {
		return null;
	}

}
