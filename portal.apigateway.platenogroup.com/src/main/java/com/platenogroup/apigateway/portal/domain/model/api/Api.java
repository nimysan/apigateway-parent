package com.platenogroup.apigateway.portal.domain.model.api;

import com.platenogroup.apigateway.portal.domain.shared.Entity;

/**
 * 聚合根 Api
 * 
 * 
 * 值对象列表:
 * ApiRoute
 * 
 * 
 * @author SeanYe
 *
 */
public class Api implements Entity<Api> {

	private ApiId id;
	private String name;
	private String description;
	private ApiRoute route;

	@Override
	public boolean sameIdentityAs(Api other) {
		return false;
	}

}
