package com.platenogroup.apigateway.common.interfaces.dto.base;

/**
 * 
 * RestController通用数据返回格式
 * 
 * @author SeanYe
 *
 * @param <T>
 */
public class PngCommonRestResponse {

	public PngCommonRestResponse(String code, String message, Object body) {
		this.code = code;
		this.message = message;
		this.body = body;
	}

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 提示信息
	 */
	private String message;

	/**
	 * 返回给终端，以方便服务查询日志
	 */
	private String traceId;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	/**
	 * 实体消息
	 */
	private Object body;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
