package com.platenogroup.apigateway.portal.domain.model.api;

import com.platenogroup.apigateway.portal.domain.shared.ValueObject;

/**
 * 这是一个值对象
 * 
 * @author SeanYe
 *
 */
public final class ApiId implements ValueObject<ApiId> {

	private static final long serialVersionUID = -3926248721289101988L;

	private String id;

	@Override
	public boolean sameValueAs(ApiId other) {
		return false;
	}

	/**
	 * 构造方法
	 * @param id
	 */
	public ApiId(String id) {
		this.id = id;
	}
}
