package com.platenogroup.apigateway.common.interfaces.exception;

/**
 *    本程序内部异常。 
 * @author SeanYe
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1184944069954989435L;

	/**
	 * exception对应的错误码
	 */
	private String code;

	/**
	 * 提示信息动态参数
	 */
	private Object[] arguments;

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public BusinessException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(String message, Object[] arguments) {
		super(message);
		this.arguments = arguments;
	}

	public BusinessException(String code, String message, Object[] arguments) {
		super(message);
		this.code = code;
		this.arguments = arguments;
	}

	public BusinessException(String message, Object[] arguments, Throwable cause) {
		super(message, cause);
		this.arguments = arguments;
	}

	public BusinessException(String code, String message, Object[] arguments, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.arguments = arguments;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessException(String code, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
