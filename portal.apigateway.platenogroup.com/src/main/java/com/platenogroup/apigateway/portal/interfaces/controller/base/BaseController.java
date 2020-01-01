package com.platenogroup.apigateway.portal.interfaces.controller.base;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.platenogroup.apigateway.common.constants.ReturnCode;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseBody;
import com.platenogroup.apigateway.common.interfaces.dto.base.ResponseDto;
import com.platenogroup.apigateway.portal.infrastructure.exception.ExceptionHandler;
import com.platenogroup.apigateway.portal.infrastructure.util.ApplicationUtil;

/**
 * 基础控制器类，提供通用的方法处理
 * 
 */
public class BaseController {

	private static Logger logger = LogManager.getLogger(BaseController.class);

	@Autowired
	private ApplicationUtil applicationUtil;

	@Autowired
	private ExceptionHandler exceptionHandler;

	/**
	 * format 失败 response。
	 * 
	 * @param e
	 * @return
	 */
	protected ResponseDto formatErrorResponse(final Exception e) {
		ResponseDto responseDto = new ResponseDto();
		// 将response 的data body置为空
		responseDto.setBody(null);

		// 依据异常类型进行分别处理，将异常信息转义为用户友好的提示信息
		Map<String, String> exceptionMap = exceptionHandler.handle(e);
		responseDto.setReturnCode(exceptionMap.get("errorCode"));
		responseDto.setReturnMsg(exceptionMap.get("errorMsg"));
		logger.debug("Response is: " + responseDto);
		return responseDto;
	}

	/**
	 * format成功的response
	 * 
	 * @param responseBody
	 * @return ResponseDto
	 */
	protected ResponseDto formatSuccessResponse(ResponseBody responseBody) {
		ResponseDto responseDto = new ResponseDto();
		// 设置返回码和返回信息为成功
		responseDto.setReturnCode(ReturnCode.SUCCESS);
		responseDto.setReturnMsg(applicationUtil.getReturnMsg(ReturnCode.SUCCESS));

		// 将response 的data body置为实际的业务body
		responseDto.setBody(responseBody);
		logger.debug("Response is: " + responseDto);
		return responseDto;
	}

}
