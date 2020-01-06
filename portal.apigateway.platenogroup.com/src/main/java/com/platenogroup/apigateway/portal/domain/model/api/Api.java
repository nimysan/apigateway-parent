package com.platenogroup.apigateway.portal.domain.model.api;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.apache.commons.lang.Validate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.google.common.collect.ImmutableSet;
import com.platenogroup.apigateway.portal.domain.shared.Entity;

/**
 * 聚合根 Api。 对外的业务意义就是Service。
 * 
 * <code>
 * <pre>
 * {
	    "id": "9748f662-7711-4a90-8186-dc02f10eb0f5",
	    "created_at": 1422386534,
	    "updated_at": 1422386534,
	    "name": "my-service",
	    "retries": 5,
	    "protocol": "http",
	    "host": "example.com",
	    "port": 80,
	    "path": "/some_api",
	    "connect_timeout": 60000,
	    "write_timeout": 60000,
	    "read_timeout": 60000,
	    "tags": ["user-level", "low-priority"],
	    "client_certificate": {"id":"4e3ad2e4-0bc4-4638-8e34-c84a417ba39b"}
	}
	</pre>
 * 
 * </code>
 * 
 * 
 * 值对象列表: ApiRoute
 * 
 * 
 * @author SeanYe
 *
 */
@EntityListeners(AuditingEntityListener.class)
@javax.persistence.Entity
public class Api implements Entity<Api> {

	@Id
	private String id;

	private String name;
	private String description;
	private int retries = 5; // default
	private String protocol; // default HTTP
	private String host;
	private int port = 80;
	private String path; // default PATH
	private byte state = 0; // 0 active 1 not active

	private int connectTimeout = 60000;
	private int writeTimeout = 60000;
	private int readTimeout = 60000;

	private Set<ApiTag> tags;

	private ApiRouteDefinition route;

	public boolean sameIdentityAs(Api other) {
		return other.getName().contentEquals(this.getName());
	}

	public Set<ApiTag> getTags() {
		return ImmutableSet.copyOf(tags);
	}

	public Api(String name, String protocol, String host, int port, String path) {
		Validate.notNull(name);
		Validate.notNull(protocol, "protocol can not be null");
		Validate.notNull(path, "path can not be null");
		Validate.notNull(host, "host can not be null");

		this.name = name;
		this.protocol = protocol;
		this.port = port;
		this.path = path;
		this.host = host;
	}

	/**
	 * 检查一个API是否已经可以使用，如果可以使用，发布ApiReady Event. API转发引擎会将API加入代理系列。
	 * 
	 * @return
	 */
	public boolean isReady() {
		if (this.getRoute() == null) {
			return false;
		}
		return this.isActive();
	}

	/**
	 * 给当前API添加TAG
	 * 
	 * @param tagValue
	 */
	public synchronized void addTag(String tagValue) {
		Validate.notNull(tagValue);
		ApiTag tag = new ApiTag(tagValue);
		if (tags == null) {
			tags = new HashSet<ApiTag>();
		}
		tags.add(tag);
	}

	/**
	 * 将一个api设置为不可用
	 */
	public void deactive() {
		if (this.state == 0) {
			this.state = 1;
			return;
		}
		throw new IllegalArgumentException("Api is alreay deative");
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRoute(ApiRouteDefinition route) {
		this.route = route;
	}

	public Api() {
		// spring-data-jpa needed
	}

	public boolean isActive() {
		return state == 0;
	}

	@Override
	public String toString() {
		return "Api [id=" + id + ", name=" + name + ", description=" + description + ", route=" + route + "]";
	}

	/**
	 * 存在則刪除，不存在不報錯
	 * 
	 * @param string
	 */
	public void removeTag(String string) {
		if (tags == null) {
			return;
		}
		tags.remove(new ApiTag(string));
	}

}
