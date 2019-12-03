package com.platenogroup.apigateway.portal.domain.model.api;

import java.util.UUID;

import javax.persistence.Id;

import org.apache.commons.lang.Validate;

import com.platenogroup.apigateway.portal.domain.shared.Entity;

/**
 * 聚合根 Api
 * 
 * 
 * 值对象列表: ApiRoute
 * 
 * 
 * @author SeanYe
 *
 */
@javax.persistence.Entity
public class Api implements Entity<Api> {

	@Id
	private ApiId id;

	private String name;
	private String description;

	public String cats() {
		return "cats";
	}

	private ApiRouteDefinition route;

	public boolean sameIdentityAs(Api other) {
		return false;
	}

	public String getRdbId() {
		return this.getId().toSimple();
	}

	public Api(String name, ApiRouteDefinition route) {
		Validate.notNull(name);
		Validate.notNull(route, "Api Route can not be null");
		this.name = name;
		this.id = new ApiId(UUID.randomUUID().toString());
		this.route = route;
	}

	public ApiId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ApiRouteDefinition getRoute() {
		return route;
	}

	public Api() {
		// spring-data-jpa needed
	}

	@Override
	public String toString() {
		return "Api [id=" + id + ", name=" + name + ", description=" + description + ", route=" + route + "]";
	}

}
