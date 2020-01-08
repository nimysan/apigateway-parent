package com.platenogroup.apigateway.dispatcher.infrastructure;

public interface RequestIdGenerator {

	/**
	 * Runtime集群唯一的请求ID
	 * 
	 * @return
	 */
	public String nextId();

}
