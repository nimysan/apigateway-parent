package com.platenogroup.apigateway.common.interfaces.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.platenogroup.apigateway.common.constants.ReturnCode;
import com.platenogroup.apigateway.common.interfaces.dto.base.PngCommonRestResponse;
import com.platenogroup.apigateway.common.interfaces.dto.base.PngRestBuilder;

@ControllerAdvice
@ResponseBody
public class PngWebExceptionHandler {
	private static Log logger = LogFactory.getLog(PngWebExceptionHandler.class);

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public PngCommonRestResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logException(e);
		return PngRestBuilder.newBuilder().failWithCode(ReturnCode.INVALID_PARAMTER);
	}

	private void logException(Throwable e) {
		if (logger.isDebugEnabled()) {
			logger.debug("Fail", e);
		}
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public PngCommonRestResponse handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		logException(e);
		return PngRestBuilder.newBuilder().failWithCode(ReturnCode.INVALID_METHOD_SUPPORT);
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public PngCommonRestResponse handleHttpMediaTypeNotSupportedException(Exception e) {
		logException(e);
		return PngRestBuilder.newBuilder().failWithCode(ReturnCode.INVALID_CONTENT_TYPE);
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public PngCommonRestResponse handleException(Exception e) {
		logException(e);
		return PngRestBuilder.newBuilder().failWithCode(ReturnCode.UNKNOWN_ERROR);
	}
}