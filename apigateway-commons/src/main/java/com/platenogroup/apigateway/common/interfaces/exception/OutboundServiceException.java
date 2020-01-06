package com.platenogroup.apigateway.common.interfaces.exception;

/**
 *   应用程序调用其他服务失败，统一抛出此异常。
 *  
 * @author SeanYe
 *
 */
public class OutboundServiceException extends RuntimeException {

	private static final long serialVersionUID = 1059079227011500198L;

	public OutboundServiceException() {
		super();
	}

	public OutboundServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OutboundServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutboundServiceException(String message) {
		super(message);
	}

	public OutboundServiceException(Throwable cause) {
		super(cause);
	}

}
