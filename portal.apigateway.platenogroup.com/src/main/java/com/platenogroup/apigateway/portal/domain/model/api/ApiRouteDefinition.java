package com.platenogroup.apigateway.portal.domain.model.api;

import com.platenogroup.apigateway.portal.domain.shared.ValueObject;

/**
 * api的转发规则定义
 * 
 * @author SeanYe
 *
 */
public class ApiRouteDefinition implements ValueObject<ApiRouteDefinition> {

	private static final long serialVersionUID = 6163471931412740722L;

	private String upstreamPath;
	private String downstreamPath;
	private String method;

	@Override
	public boolean sameValueAs(ApiRouteDefinition other) {
		// TODO
		return false;
	}

	public String getUpstreamPath() {
		return upstreamPath;
	}

	public void setUpstreamPath(String upstreamPath) {
		this.upstreamPath = upstreamPath;
	}

	public String getDownstreamPath() {
		return downstreamPath;
	}

	public void setDownstreamPath(String downstreamPath) {
		this.downstreamPath = downstreamPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
