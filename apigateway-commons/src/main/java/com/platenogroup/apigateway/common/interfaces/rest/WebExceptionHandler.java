package com.platenogroup.apigateway.common.interfaces.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Objects;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseDto;

@ControllerAdvice
@ResponseBody
public class WebExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);
	private static final Object EMPTY = "";
//    /**
//     * 400 - Bad Request
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ServiceResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
//        logger.error("参数解析失败", e);
//        return ServiceResponseHandle.failed("could_not_read_json");
//    }
//    
//    /**
//     * 405 - Method Not Allowed
//     */
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ServiceResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//        logger.error("不支持当前请求方法", e);
//        return ServiceResponseHandle.failed("request_method_not_supported");
//    }
//
//    /**
//     * 415 - Unsupported Media Type
//     */
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ServiceResponse handleHttpMediaTypeNotSupportedException(Exception e) {
//        logger.error("不支持当前媒体类型", e);
//        return ServiceResponseHandle.failed("content_type_not_supported");
//    }

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	public ResponseDto handleException(Exception e) {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setReturnCode("1234");
		responseDto.setReturnMsg(e.getMessage());
		responseDto.setBody(EMPTY);
		return responseDto;
//		if (e instanceof BusinessException) {
//			
//		}
//
//		logger.error("服务运行异常", e);
//		return ServiceResponseHandle.failed("server_error");
	}
}