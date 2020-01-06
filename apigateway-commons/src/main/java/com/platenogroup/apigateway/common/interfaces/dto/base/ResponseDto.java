package com.platenogroup.apigateway.common.interfaces.dto.base;

/**
 * Controller的响应DTO
 * 
 * @author SeanYe
 *
 * @param <T>
 */
public class ResponseDto<T> implements UIDto<T> {

	/**
	 * 状态码
	 */
	private String returnCode;

	/**
	 * 提示信息
	 */
	private String returnMsg;

	/**
	 * 各个接口返回的数据
	 */
	private T body;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

}
