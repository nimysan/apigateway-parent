package com.platenogroup.apigateway.portal.infrastructure.id;

/**
 * 产生集群唯一ID
 * 
 * @author SeanYe
 *
 */
public interface DistributedIdGenerator {

	public String nextId();
}
