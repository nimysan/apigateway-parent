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

	private String downstreamHost;
	private String downstreamPath;
	private String method;

	@Override
	public String toString() {
		return String.format("ApiRouteDefinition [downstreamHost=%s, downstreamPath=%s, method=%s]", downstreamHost,
				downstreamPath, method);
	}

	@Override
	public boolean sameValueAs(ApiRouteDefinition other) {
		return false;
	}

	public String getDownstreamHost() {
		return downstreamHost;
	}

	public void setDownstreamHost(String downstreamHost) {
		this.downstreamHost = downstreamHost;
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
