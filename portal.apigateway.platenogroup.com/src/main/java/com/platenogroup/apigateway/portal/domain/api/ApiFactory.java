package com.platenogroup.apigateway.portal.domain.api;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import com.platenogroup.apigateway.portal.infrastructure.system.SystemContext;
import com.vluee.ddd.support.domain.AggregateId;

/**
 * 很多的辅助设施都是为了不让Domain模型层被 持久化等逻辑所腐化
 * 
 * @author SeanYe
 *
 */
@Component
public class ApiFactory {

	@Inject
	private AutowireCapableBeanFactory spring;

	public Api createApi(String name, String accessPath, String description) {
		Api api = new Api(AggregateId.generate(), SystemContext.getWorkuser(), name, accessPath, description);
		api.setCreatedAt(new Date().getTime());
//		spring.autowireBean(api);
		return api;
	}
}
